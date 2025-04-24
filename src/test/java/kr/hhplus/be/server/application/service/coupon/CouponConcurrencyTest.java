package kr.hhplus.be.server.application.service.coupon;

import kr.hhplus.be.server.application.port.out.CouponPort;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.type.DiscountType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class CouponConcurrencyTest {

    @Autowired
    private CouponPort couponPort;

    @Autowired
    private CouponService couponService;

    private final long ANY_USER_ID = 1L;
    private final BigDecimal discountValue = new BigDecimal("30000");
    private final BigDecimal minOrderAmount = new BigDecimal("15.0");
    private final BigDecimal maxDiscountAmount = new BigDecimal("10000");
    private final int totalCount = 5;

    Coupon coupon;

    @BeforeEach
    void setup() {
        coupon = getCoupon();
        coupon.totalCount = 3;
        coupon.issuedCount = 0;
        couponPort.save(coupon);
    }

    @Test
    void 동시성_문제_발생_락_없이() throws InterruptedException {
        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Coupon couponTest = couponPort.findById(coupon.getId());

        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                try {
                    couponService.issueCoupon(ANY_USER_ID, couponTest.getId());
                } catch (Exception e) {
                    // 쿠폰 소진 or 동시성 문제
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Coupon result = couponPort.findById(couponTest.getId());
        System.out.println("처음 발급전 수량: " + result.getTotalCount());
        System.out.println("[락 없이] 최종 발급 수량: " + coupon.getIssuedCount());
        Assertions.assertTrue(result.getIssuedCount() <= 2);
    }

    @Test
    void pessimisticLockingTest() throws InterruptedException {
        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Coupon couponTest = couponPort.findById(coupon.getId());

        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                try {
                    couponService.issueCoupon(ANY_USER_ID, couponTest.getId());
                } catch (IllegalStateException e) {
                    System.out.println("쿠폰 소진");
                    System.out.println("e " + e);
                }
                latch.countDown();
            });
        }

        latch.await();

        Coupon result = couponPort.findById(couponTest.getId());
        System.out.println("처음 발급전 수량: " + result.getTotalCount());
        System.out.println("[비관적 락]최종 발급된 수량: " + result.getIssuedCount());
        Assertions.assertTrue(result.getIssuedCount() <= 2);
    }

    private Coupon getCoupon() {
        return new Coupon("NEWYEAR2025",
                DiscountType.FIXED,
                discountValue,
                minOrderAmount,
                maxDiscountAmount,
                LocalDateTime.of(2025, 1, 1, 0, 0),
                LocalDateTime.of(2025, 12, 31, 23, 59),
                true,
                totalCount
        );
    }

}

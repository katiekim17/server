package kr.hhplus.be.server.application.service.coupon;

import kr.hhplus.be.server.application.port.out.CouponPort;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.IssuedCoupon;
import kr.hhplus.be.server.domain.type.DiscountType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @Mock
    private CouponPort couponPort;

    @InjectMocks
    private CouponService couponService;

    private final long ANY_USER_ID = 1L;
    private final Long ANY_COUPON_ID = 1L;
    private final Long maxIssueAmount = 1000L;
    private final BigDecimal discountValue = new BigDecimal("30000");
    private final BigDecimal minOrderAmount = new BigDecimal("15.0");
    private final BigDecimal maxDiscountAmount = new BigDecimal("10000");
    private final int totalCount = 10;
    private final int issuedCount = 0;
    private Coupon coupon;
    private IssuedCoupon issuedCoupon;

    @Test
    @DisplayName("쿠폰을 성공적으로 발행한다")
    void issueCoupon() {
        // given - 등록할 쿠폰 정보가 있어야 하고
        // 쿠폰 디비에 등록하면 아래가 나와야함.
        Coupon coupon = getCoupon();

        when(couponPort.save(any())).thenReturn(coupon);

        // when - 디비에 저장
        Coupon result = couponService.createCoupon(coupon);

        // then - 디비에 내가 저장한 쿠폰이 있는가 확인
        Assertions.assertNotNull(result);
        Assertions.assertEquals("NEWYEAR2025", result.getCode());
        Assertions.assertEquals(DiscountType.FIXED, result.getDiscountType());
        verify(couponPort, times(1)).save(any(Coupon.class));

    }


    @Test
    @DisplayName("유저에게 쿠폰 발급한다")
    void issueCouponToUser(){
        // given
        Coupon coupon = getCoupon();

        // 쿠폰 아이디로 쿠폰을 조회한다
        // Optional을 쓰면 “여기에 값이 있을 수도, 없을 수도 있다”는 의도를 명시
        // of -> coupon != null
        when(couponPort.findCouponById(ANY_COUPON_ID)).thenReturn(coupon);

        // when
        IssuedCoupon actual = couponService.issueCoupon(ANY_USER_ID, ANY_COUPON_ID);

        // then
        verify(couponPort,times(1)).saveIssueCoupon(any(IssuedCoupon.class));   // 저장 호출 검증

    }


    @Test
    @DisplayName("유저가 쿠폰 사용한다")
    void useCoupon(){

        // given
        Coupon coupon = getCoupon();

        IssuedCoupon issuedCoupon = getIssuedCoupon(coupon);

        // 쿠폰 아이디로 쿠폰을 조회한다
        // Optional을 쓰면 “여기에 값이 있을 수도, 없을 수도 있다”는 의도를 명시
        // 정의 하는 구간
        when(couponPort.findCouponByUserId(ANY_COUPON_ID, ANY_USER_ID)).thenReturn(issuedCoupon);
        when(couponPort.saveIssueCoupon(any(IssuedCoupon.class))).thenReturn(issuedCoupon);

        // when
        IssuedCoupon actual = couponService.useCoupon(ANY_USER_ID, ANY_COUPON_ID);

        // then
        assertThat(actual.getUserId()).isEqualTo(ANY_USER_ID);
        assertThat(actual.getCoupon()).isEqualTo(coupon);
        verify(couponPort,times(1)).saveIssueCoupon(any(IssuedCoupon.class));   // 저장 호출 검증

    }

    private @NotNull IssuedCoupon getIssuedCoupon(Coupon coupon) {
        return new IssuedCoupon(
                ANY_USER_ID,
                coupon,
                LocalDateTime.of(2025, 1, 1, 0, 0),
                false,
                LocalDateTime.of(2025, 4, 4, 5, 0)
        );
    }

    private Coupon getCoupon() {
        return new Coupon(
                "NEWYEAR2025",
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

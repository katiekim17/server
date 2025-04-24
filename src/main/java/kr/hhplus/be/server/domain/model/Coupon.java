package kr.hhplus.be.server.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.type.DiscountType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "coupon")
@EntityListeners(AuditingEntityListener.class)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private DiscountType discountType; // 할인 타입 (FIXED, PERCENTAGE)
    private BigDecimal discountValue; // 할인 금액
    private BigDecimal minOrderAmount; // 최소 주문 금액
    private BigDecimal maxDiscountAmount; // 최대 할인 금액
    private LocalDateTime startDate; // 쿠폰 사용 시작일
    private LocalDateTime endDate;  // 쿠폰 사용 종료일
    private boolean isActive; // 쿠폰 사용 가능 여부
    public int totalCount; // 쿠폰 발행 가능 수량
    public int issuedCount; // 쿠폰 발행된 수량

    @CreationTimestamp
    private LocalDateTime createdAt; // 쿠폰 등록일

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 쿠폰 수정일

//    @Version // 낙관적 락에서 사용
//    private Long version;

//    public static Coupon of(String code, DiscountType discountType, BigDecimal discountValue,
//                            BigDecimal minOrderAmount, BigDecimal maxDiscountAmount,
//                            LocalDateTime startDate, LocalDateTime endDate,
//                            boolean isActive) {
//
//        Coupon coupon = new Coupon();
//        coupon.code = code;
//        coupon.discountType = discountType;
//        coupon.discountValue = discountValue;
//        coupon.minOrderAmount = minOrderAmount;
//        coupon.maxDiscountAmount = maxDiscountAmount;
//        coupon.startDate = startDate;
//        coupon.endDate = endDate;
//        coupon.isActive = isActive;
//        coupon.createdAt = LocalDateTime.now();
//        coupon.updatedAt = LocalDateTime.now();
//        coupon.issuedCount = 0;
//        return coupon;
//    }

    public Coupon(String code, DiscountType discountType, BigDecimal discountValue, BigDecimal minOrderAmount, BigDecimal maxDiscountAmount, LocalDateTime startDate, LocalDateTime endDate, boolean isActive, int totalCount) {
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minOrderAmount = minOrderAmount;
        this.maxDiscountAmount = maxDiscountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.totalCount = totalCount;
        this.issuedCount = 0;

        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }


    public Coupon getCoupon(Coupon reqCoupon) {

        if (reqCoupon == null) {
            throw new IllegalArgumentException("쿠폰이 없음: " + reqCoupon.getId());
        }

        return new Coupon(
                reqCoupon.getCode(),
                reqCoupon.getDiscountType(),
                reqCoupon.getDiscountValue(),
                reqCoupon.getMinOrderAmount(),
                reqCoupon.getMaxDiscountAmount(),
                reqCoupon.getStartDate(),
                reqCoupon.getEndDate(),
                reqCoupon.isActive(),
                reqCoupon.getTotalCount()
        );
    }

    public IssuedCoupon issueToUser(Long userId) {

        if (userId == null) {
            throw new IllegalArgumentException("유저가 없음");
        }

        issue();

        return new IssuedCoupon(
                userId,
                this,
                LocalDateTime.now(),
                false,
                null
        );
    }

    // 쿠폰 발행 숫자 만큼 전체 쿠폰 수량 차감
    public void issueCount() {
        this.issue();
        this.totalCount--;
    }

    public void issue(){
        if (this.issuedCount >= this.totalCount) {
            throw new IllegalStateException("쿠폰 발행 수량 초과");
        }
        if (this.totalCount <= 0) {
            throw new IllegalStateException("쿠폰 수량이 없음");
        }
        if (this.issuedCount <= 0) {
            throw new IllegalStateException("쿠폰 발행 수량이 없음");
        }
        this.issuedCount++;
    }


}

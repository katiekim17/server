package kr.hhplus.be.server.interfaces.api.coupon;

import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.type.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponResponse(
        Long couponId,
        String code,
        DiscountType discountType,
        BigDecimal discountValue,
        BigDecimal minOrderAmount,
        BigDecimal maxDiscountAmount,
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CouponResponse from(Coupon coupon) {
        return new CouponResponse(
                coupon.getId(),
                coupon.getCode(),
                coupon.getDiscountType(),
                coupon.getDiscountValue(),
                coupon.getMinOrderAmount(),
                coupon.getMaxDiscountAmount(),
                coupon.getStartDate(),
                coupon.getEndDate(),
                coupon.isActive(),
                coupon.getCreatedAt(),
                coupon.getUpdatedAt()
        );
    }
}
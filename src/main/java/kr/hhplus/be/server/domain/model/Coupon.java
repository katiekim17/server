package kr.hhplus.be.server.domain.model;

import kr.hhplus.be.server.domain.type.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Coupon {
    private final Long id;
    private final String code;
    private final DiscountType discountType;
    private final BigDecimal discountValue;
    private final BigDecimal minOrderAmount;
    private final BigDecimal maxDiscountAmount;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final boolean isActive;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Coupon(Long id, String code, DiscountType discountType, BigDecimal discountValue, BigDecimal minOrderAmount, BigDecimal maxDiscountAmount, LocalDateTime startDate, LocalDateTime endDate, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minOrderAmount = minOrderAmount;
        this.maxDiscountAmount = maxDiscountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

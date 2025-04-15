package kr.hhplus.be.server.application.port.out;

import kr.hhplus.be.server.domain.model.Coupon;

import java.util.List;

public interface CouponPort {
    Coupon getAvailableCoupons(Long userId);
}

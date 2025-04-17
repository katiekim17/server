package kr.hhplus.be.server.application.port.out;

import kr.hhplus.be.server.domain.model.Coupon;

import java.util.List;


public interface CouponPort {
    List<Coupon> getMyCoupons(Long userId);
    Coupon getAvailableCoupons(Long userId);
    void save(Coupon coupon);
}

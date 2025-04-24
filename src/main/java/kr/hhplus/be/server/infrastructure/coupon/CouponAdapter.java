package kr.hhplus.be.server.infrastructure.coupon;

import kr.hhplus.be.server.application.port.out.CouponPort;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.IssuedCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CouponAdapter implements CouponPort {

    private final CouponRepository couponRepository;
    private final IssuedCouponRepository issuedCouponRepository;

//    @Override
//    public Coupon getAvailableCoupons(Long userId) {
//        return null;
//    }

//    @Override
//    public List<Coupon> getMyCoupons (Long userId) {
//        return couponRepository.findAllByUserId(userId);
//    }
//
    @Override
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    // A유저에게 B쿠폰 발급
    @Override
    public IssuedCoupon saveIssueCoupon(IssuedCoupon issuedCoupon) {
        return issuedCouponRepository.save(issuedCoupon);
    }

    @Override
    public Coupon findCouponById(Long userId) {
        return couponRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("쿠폰을 찾을 수 없습니다."));
    }

    @Override
    public IssuedCoupon findCouponByUserId(Long issuedCouponId, Long userId) {
        return issuedCouponRepository.findByIdAndUserId(issuedCouponId, userId);
    }


}

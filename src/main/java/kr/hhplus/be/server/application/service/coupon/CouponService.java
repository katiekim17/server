package kr.hhplus.be.server.application.service.coupon;

import kr.hhplus.be.server.application.port.in.GetCouponUseCase;
import kr.hhplus.be.server.application.port.out.CouponPort;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.IssuedCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponService implements GetCouponUseCase {

    private final CouponPort couponPort;


    @Override
    public Coupon createCoupon(Coupon reqCoupon) {
        Coupon coupon = new Coupon(
                reqCoupon.getCode(),
                reqCoupon.getDiscountType(),
                reqCoupon.getDiscountValue(),
                reqCoupon.getMinOrderAmount(),
                reqCoupon.getMaxDiscountAmount(),
                reqCoupon.getStartDate(),
                reqCoupon.getEndDate(),
                reqCoupon.isActive(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return couponPort.save(coupon);
    }


    @Override
    public IssuedCoupon issueCoupon(Long userId, Long couponId) {

        // 발행할 쿠폰 찾기
        Coupon coupon = couponPort.findCouponById(couponId);
        if (coupon == null) {
            throw new IllegalArgumentException("쿠폰이 없음: " + couponId);
        }

        // 유저에게 쿠폰 발급
        IssuedCoupon issued = new IssuedCoupon(
                userId,
                coupon,
                LocalDateTime.now(),
                false,
                null
        );

        return couponPort.saveIssueCoupon(issued);
    }


    @Override
    public IssuedCoupon useCoupon(Long userId, Long couponId) {

        // 유저에게 발행한 쿠폰 찾기
        IssuedCoupon coupon = couponPort.findCouponByUserId(couponId, userId);
        if (coupon == null) {
            throw new IllegalArgumentException("쿠폰이 없음: " + couponId);
        }

        // 이미 사용된 쿠폰인지 확인z
        if (coupon.isUsed()) {
            throw new IllegalArgumentException("이미 사용된 쿠폰임: " + couponId);
        }

        // 발급한 쿠폰이 사용한 상태로 상태변환
        coupon.markAsUsed(LocalDateTime.now());

        return couponPort.saveIssueCoupon(coupon);
    }


}

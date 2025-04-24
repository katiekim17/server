package kr.hhplus.be.server.application.service.coupon;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.application.port.in.GetCouponUseCase;
import kr.hhplus.be.server.application.port.out.CouponPort;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.IssuedCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CouponService implements GetCouponUseCase {

    private final CouponPort couponPort;

    @Override
    public Coupon createCoupon(Coupon reqCoupon) {
        return couponPort.save(reqCoupon.getCoupon(reqCoupon));
    }

    @Transactional
    @Override
    public IssuedCoupon issueCoupon(Long userId, Long couponId) {

        // 발행할 쿠폰 찾기
        Coupon coupon = couponPort.findCouponById(couponId);

        // 쿠폰 발행 숫자 만큼 전체 쿠폰 수량 차감
        coupon.issueCount();
        couponPort.save(coupon);

        // 유저에게 쿠폰 발급
        return couponPort.saveIssueCoupon(coupon.issueToUser(userId));

    }



    @Override
    public IssuedCoupon useCoupon(Long userId, Long couponId) {

        // 유저에게 발행한 쿠폰 찾기
        IssuedCoupon coupon = couponPort.findCouponByUserId(couponId, userId);

        coupon.use();

        return couponPort.saveIssueCoupon(coupon);
    }


}

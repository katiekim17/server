package kr.hhplus.be.server.application.port.in;

import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.IssuedCoupon;

//  사용자가 하고싶은 일(유즈케이스)를 부르는 문
public interface GetCouponUseCase {

    Coupon createCoupon(Coupon coupon); // req에서 Coupon 형식으로 데이터가 오면 디비로 저장
    IssuedCoupon issueCoupon(Long userId, Long couponId);
    IssuedCoupon useCoupon(Long userId, Long couponId);

}

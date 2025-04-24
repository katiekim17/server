package kr.hhplus.be.server.application.port.out;

import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.IssuedCoupon;

import java.util.Optional;


public interface CouponPort {
    Coupon save(Coupon coupon); // 쿠폰 등록
    IssuedCoupon saveIssueCoupon(IssuedCoupon issuedCoupon); // 유저에게 쿠폰 발행
    IssuedCoupon findCouponByUserId(Long issuedCouponId, Long userId); // 유저에게 쿠폰 발행
    Coupon findCouponById(Long couponId); // 쿠폰 아이디로 쿠폰 찾기
//  Coupon findCouponByUserId(Long userId); // 쿠폰 아이디로 쿠폰 찾기
}
package kr.hhplus.be.server.infrastructure.coupon;

import kr.hhplus.be.server.domain.model.IssuedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Long> {
    IssuedCoupon findByIdAndUserId(Long issuedCouponId, Long userId);
    IssuedCoupon save(IssuedCoupon issuedCoupon);
}

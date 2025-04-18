package kr.hhplus.be.server.infrastructure.coupon;

import kr.hhplus.be.server.domain.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByUserId(Long userId);
//    List<Coupon> loadPointHistory(Long userId);
    Coupon save(Coupon coupon);
}

package kr.hhplus.be.server.infrastructure.coupon;

import kr.hhplus.be.server.domain.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
//    List<Coupon> findAllByUserId(Long userId);
//    List<Coupon> loadPointHistory(Long userId);
//    Coupon save(Coupon coupon);   -> jpa 에서 기본적으로 제공하고 있기 때문에 없어도 됨!
}

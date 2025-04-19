package kr.hhplus.be.server.infrastructure.coupon;

import kr.hhplus.be.server.application.port.out.CouponPort;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.infrastructure.inventory.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CouponAdapter implements CouponPort {

    private final CouponRepository couponRepository;

    @Override
    public Coupon getAvailableCoupons(Long userId) {
        return null;
    }

    @Override
    public List<Coupon> getMyCoupons (Long userId) {
        return couponRepository.findAllByUserId(userId);
    }

    @Override
    public void save(Coupon coupon) {
        couponRepository.save(coupon);
    }


}

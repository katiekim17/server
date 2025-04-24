package kr.hhplus.be.server.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "issued_coupon")
public class IssuedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    private LocalDateTime issuedAt;

    private boolean isUsed;

    private LocalDateTime usedAt;

    public IssuedCoupon(Long userId, Coupon coupon, LocalDateTime issuedAt, boolean isUsed, LocalDateTime usedAt) {
        this.userId = userId;
        this.coupon = coupon;
        this.issuedAt = issuedAt;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
    }

    public IssuedCoupon getIssued(Long userId, Coupon coupon) {
        if (coupon == null) {
            throw new IllegalArgumentException("쿠폰이 없음: " + this.getCoupon().getId());
        }

        // 이미 사용된 쿠폰인지 확인
        if (this.isUsed) {
            throw new IllegalArgumentException("이미 사용된 쿠폰임: " + this.getCoupon().getId());
        }

        return new IssuedCoupon(
                userId,
                coupon,
                LocalDateTime.now(),
                false,
                null
        );
    }

    // 이미 발행된 상태로 쿠폰 상태변환
    public void markAsUsed(LocalDateTime usedAt) {
        this.isUsed = true;
        this.usedAt = usedAt;
    }

    public void use() {

        // 발급한 쿠폰이 사용한 상태로 상태변환
        markAsUsed(LocalDateTime.now());

    }
}

package kr.hhplus.be.server.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserPoint {
    private final Long userId;
    private Long point;
    private LocalDateTime updatedAt;

    static final long MAX_POINT_LIMIT = 1000L;

    public UserPoint(Long userId, Long point, LocalDateTime updatedAt) {
        if (point < 0) throw new IllegalArgumentException("포인트는 0 이상이어야 합니다");
        this.userId = userId;
        this.point = point;
        this.updatedAt = updatedAt;
    }

    public void charge(Long amount) {
        if (amount <= 0) throw new IllegalArgumentException("충전 금액은 0보다 커야 합니다");
        if (amount > MAX_POINT_LIMIT) throw new IllegalArgumentException(String.format("최대 충전값은 %d입니다", MAX_POINT_LIMIT));  // 정책이지만, 100 초과는 충전할 수 없다
        this.point += amount;
        this.updatedAt = LocalDateTime.now();
    }

    public void use(Long amount) {
        if (amount <= 0) throw new IllegalArgumentException("사용 금액은 0보다 커야 합니다");
        if (this.point < amount) throw new IllegalStateException("포인트가 부족합니다");
        this.point -= amount;
        this.updatedAt = LocalDateTime.now();
    }

//    public void pointValidation(long chargeAmount){
//        // 들어온 포인트가 양수인지 확인, 음수이면 안됨
//        if (chargeAmount < 0) {
//            throw new IllegalArgumentException("잘못된 충전 금액을 입력하셨습니다.");
//        }
//
//        // 정책이지만, 100 초과는 충전할 수 없다
//        if (chargeAmount > MAX_POINT_LIMIT) {
//            throw new IllegalArgumentException(String.format("최대 충전값은 %d입니다", MAX_POINT_LIMIT));
//        }
//    }


//    public UserPoint empty(long id) {
//        return new UserPoint(id, 0, System.currentTimeMillis());
//    }

}


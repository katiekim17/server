package kr.hhplus.be.server.infrastructure.point;

import kr.hhplus.be.server.domain.model.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface PointRepository extends JpaRepository<UserPoint, Long> {
    Optional<UserPoint> findByUserId(Long userId);
}
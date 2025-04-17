package kr.hhplus.be.server.infrastructure.point;

import kr.hhplus.be.server.domain.model.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
//    List<PointHistory> findByUserId(Long userId);
//    List<PointHistory> loadPointHistory(Long userId);
}

package kr.hhplus.be.server.infrastructure.point;

import kr.hhplus.be.server.application.port.out.PointPort;
import kr.hhplus.be.server.domain.model.PointHistory;
import kr.hhplus.be.server.domain.model.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointAdapter implements PointPort {

    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    public UserPoint loadUserPoint(Long userId) {
        return pointRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public void savePoint(UserPoint userPoint) {
        pointRepository.save(userPoint);
    }

    @Override
    public List<PointHistory> loadPointHistory(Long userId) {
        return null;
//      return pointHistoryRepository.loadPointHistory(userId);
    }

    @Override
    public void saveHistory(PointHistory history) {
        pointHistoryRepository.save(history);
    }

    @Override
    public void usePoint(UserPoint userPoint) {

    }


}

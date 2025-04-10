package kr.hhplus.be.server.application.port.out;

import kr.hhplus.be.server.domain.model.PointHistory;
import kr.hhplus.be.server.domain.model.UserPoint;

import java.util.List;

public interface PointPort {
    UserPoint loadUserPoint(Long id);
    void savePoint(UserPoint userPoint);
    List<PointHistory> loadPointHistory(Long id);
    void saveHistory(PointHistory history);

    void usePoint(UserPoint userPoint);
}

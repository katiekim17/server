package kr.hhplus.be.server.application.service.point;

import kr.hhplus.be.server.application.port.in.GetUserPointUseCase;
import kr.hhplus.be.server.application.port.out.PointPort;
import kr.hhplus.be.server.domain.model.PointHistory;
import kr.hhplus.be.server.domain.model.UserPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static kr.hhplus.be.server.domain.type.TransactionType.CHARGE;
import static kr.hhplus.be.server.domain.type.TransactionType.USE;

@Service
public class PointService implements GetUserPointUseCase {

    private final PointPort pointPort;

    public PointService(PointPort pointPort) {
        this.pointPort = pointPort;
    }

    @Override
    public UserPoint getUserPoint(long userId) {
        return pointPort.loadUserPoint(userId);
    }

    @Override
    public List<PointHistory> getPointHistory(long id) {
        UserPoint userPoint = getUserPoint(id);
        return pointPort.loadPointHistory(userPoint.getUserId());
    }

    @Override
    public UserPoint setPointCharge(long userId, long amount) {
        UserPoint userPoint = getUserPoint(userId);
        userPoint.charge(amount);
        pointPort.savePoint(userPoint);
        pointPort.saveHistory(new PointHistory(userId, amount, CHARGE, LocalDateTime.now()));
        return userPoint;
    }

    @Override
    public UserPoint setPointUse(long userId, long amount) {
        UserPoint userPoint = getUserPoint(userId);
        userPoint.use(amount);
        pointPort.usePoint(userPoint);
        pointPort.saveHistory(new PointHistory(userId, amount, USE, LocalDateTime.now()));
        return userPoint;
    }

}

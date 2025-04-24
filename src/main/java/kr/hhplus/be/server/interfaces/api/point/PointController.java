package kr.hhplus.be.server.interfaces.api.point;

import kr.hhplus.be.server.application.port.in.GetUserPointUseCase;
import kr.hhplus.be.server.domain.model.PointHistory;
import kr.hhplus.be.server.domain.model.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController implements PointApi{

    private final GetUserPointUseCase getUserPointUseCase;

    @Override
    public ResponseEntity<PointResponse> getUserPoint(Long userId) {
        UserPoint point = getUserPointUseCase.getUserPoint(userId);
        return ResponseEntity.ok(PointResponse.from(point));
    }

    @Override
    public ResponseEntity<List<PointResponse>> getPointHistory(Long userId) {
        List<PointHistory> histories = getUserPointUseCase.getPointHistory(userId);
        List<PointResponse> responseList = histories.stream()
                .map(h -> new PointResponse(userId, h.getAmount()))
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<PointResponse> setCharge(long userId, long amount) {
        UserPoint point = getUserPointUseCase.doPointCharge(userId, amount);
        return ResponseEntity.ok(PointResponse.from(point));
    }

    @Override
    public ResponseEntity<PointResponse> usePoint(long userId, long amount) {
        UserPoint point = getUserPointUseCase.doPointUse(userId, amount);
        return ResponseEntity.ok(PointResponse.from(point));
    }
}
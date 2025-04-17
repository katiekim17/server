//package kr.hhplus.be.server.interfaces.api.point;
//
//import kr.hhplus.be.server.TransactionType;
//import kr.hhplus.be.server.application.port.in.GetUserPointUseCase;
//import kr.hhplus.be.server.domain.model.PointHistory;
//import kr.hhplus.be.server.domain.model.UserPoint;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/point")
//@RequiredArgsConstructor
//public class PointController implements PointApi{
//
//    private final GetUserPointUseCase getUserPointUseCase;
//
//    @Override
//    public ResponseEntity<PointResponse> getUserPoint(Long id) {
//        UserPoint point = getUserPointUseCase.getUserPoint(id);
//        return ResponseEntity.ok(PointResponse.from(point));
//    }
//
//    @Override
//    public ResponseEntity<List<PointResponse>> getPointHistory(Long id) {
//        UserPoint point = getUserPointUseCase.getPointHistory(id);
//        return ResponseEntity.ok(PointResponse.from(point));
//    }
//
//    @Override
//    public ResponseEntity<PointResponse> setCharge(long id, long amount) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<List<PointResponse>> getProductByName(long id, long amount) {
//        return null;
//    }
//}
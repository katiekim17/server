package kr.hhplus.be.server.application.service.point;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.application.port.out.PointPort;
import kr.hhplus.be.server.domain.model.UserPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional // 테스트 후 롤백
class PointServiceIntegrationTest {

    @Autowired
    private PointService getPointService;

    @Autowired
    private PointPort pointPort;

    static final long ANY_USER_ID = 1L; // 아이디

    @Test
    @DisplayName("포인트 충전 - MySQL 통합 테스트")
    void pointCharge() {
        // given
        long userId = 1L;
        long initialPoint = 100L;
        long chargeAmount = 200L;

        UserPoint userPoint = new UserPoint(initialPoint, LocalDateTime.now());
        pointPort.savePoint(userPoint);
//        pointRepository.save(userPoint);

//        // when
//        UserPoint result = getPointService.doPointCharge(userId, chargeAmount);
//
//        // then
//        assertThat(result.getPoint()).isEqualTo(300L);

//        List<PointHistory> histories = pointHistoryRepository.findByUserId(userId);
//        assertThat(histories).hasSize(1);
//        assertThat(histories.get(0).getType()).isEqualTo(TransactionType.CHARGE);
    }
}
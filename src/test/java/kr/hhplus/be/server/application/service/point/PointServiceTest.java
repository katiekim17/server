package kr.hhplus.be.server.application.service.point;

import kr.hhplus.be.server.application.port.in.GetUserPointUseCase;
import kr.hhplus.be.server.application.port.out.PointPort;
import kr.hhplus.be.server.domain.model.PointHistory;
import kr.hhplus.be.server.domain.model.UserPoint;
import kr.hhplus.be.server.domain.type.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @Mock
    private PointPort pointPort;
    @InjectMocks
    private PointService getPointService;

    GetUserPointUseCase useCase;

    static final LocalDateTime ANY_UPDATE_MILLIS = LocalDateTime.now(); // 시간
    static final long ANY_ID = 1L; // 아이디
    static final long ANY_USER_ID = 1L; // 아이디

    @Test
    @DisplayName("특정 유저의 포인트를 조회")
    void getUserPointById() {

        // given
        UserPoint expected = new UserPoint(ANY_USER_ID, 100L, ANY_UPDATE_MILLIS);
        when(pointPort.loadUserPoint(ANY_USER_ID)).thenReturn(expected);

        // when
        useCase = getPointService;
        UserPoint result = useCase.getUserPoint(ANY_USER_ID);

        // then
        assertEquals(expected.getUserId(), result.getUserId());
        assertEquals(expected.getPoint(), result.getPoint());
    }


    @Test
    @DisplayName("특정 유저의 포인트 충전/이용 내역을 조회하는 기능 - 성공케이스")
    void getPointHistory() {

        // given
        List<PointHistory> historyList = new ArrayList<>();
        UserPoint expected = new UserPoint(ANY_USER_ID, 100L, ANY_UPDATE_MILLIS);
        PointHistory expectedPointHistory = new PointHistory(ANY_USER_ID,100L, TransactionType.USE, ANY_UPDATE_MILLIS);
        PointHistory expectedPointHistory2 = new PointHistory(ANY_USER_ID,-300L, TransactionType.CHARGE, ANY_UPDATE_MILLIS);
        historyList.add(expectedPointHistory);
        historyList.add(expectedPointHistory2);

        when(pointPort.loadUserPoint(ANY_USER_ID)).thenReturn(expected);
        when(pointPort.loadPointHistory(ANY_USER_ID)).thenReturn(historyList);

        // when
        List<PointHistory> historyListResult= getPointService.getPointHistory(expected.getUserId());

        // then
        assertThat(historyListResult).isEqualTo(historyList);

    }

    @Test
    @DisplayName("포인트를 충전한다")
    void doPointCharge() {
        // 성공케이스 - 유저정보가 있는 상태에서, Charge 하면 기존 포인트 값(100L) + 입력한 포인트(200L) = newPoint(300L) 값이 반환됨
        // given
        long initPoint = 100L;
        long chargePoint = 300L;
        long expectedPoint = initPoint + chargePoint;
        UserPoint beforeCharge = new UserPoint(ANY_USER_ID, initPoint, ANY_UPDATE_MILLIS); // 가짜 데이터 만듦
        when(pointPort.loadUserPoint(ANY_USER_ID)).thenReturn(beforeCharge);

        // when
        UserPoint result = getPointService.doPointCharge(ANY_USER_ID, chargePoint);

        // then
        // 포인트가 정상 충전 되었는지
        assertEquals(expectedPoint, result.getPoint());

        // savePoint가 호출되었는지 확인
        verify(pointPort, times(1)).savePoint(result);

        // saveHistory가 호출되었는지
        verify(pointPort).saveHistory(any(PointHistory.class));

    }

    @Test
    @DisplayName("포인트를 사용한다")
    void doPointUse() {
        // given
        long initPoint = 300L;
        long usePoint = 100L;
        long expectedPoint = initPoint - usePoint;
        UserPoint beforeCharge = new UserPoint(ANY_USER_ID, initPoint, ANY_UPDATE_MILLIS); // 가짜 데이터 만듦
        when(pointPort.loadUserPoint(ANY_USER_ID)).thenReturn(beforeCharge);

        // when
        UserPoint result = getPointService.doPointUse(ANY_USER_ID, usePoint);

        // then
        // 포인트가 정상 충전 되었는지
        assertEquals(expectedPoint, result.getPoint());

        // savePoint가 호출되었는지 확인
        verify(pointPort, times(1)).usePoint(result);

        // saveHistory가 호출되었는지
        verify(pointPort).saveHistory(any(PointHistory.class));
    }

}
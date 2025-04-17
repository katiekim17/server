package kr.hhplus.be.server.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserPointTest {

    @Test
    @DisplayName("포인트 충전에 성공한다")
    void charge() {
        UserPoint userPoint = new UserPoint(1L, 100L, LocalDateTime.now());

        // when
        userPoint.charge(200L);

        // then
        assertEquals(300L, userPoint.getPoint());
    }

    @Test
    @DisplayName("포인트 사용에 성공한다")
    void use() {
        // given
        UserPoint userPoint = new UserPoint(1L, 300L, LocalDateTime.now());

        // when
        userPoint.use(200L);

        // then
        assertEquals(100L, userPoint.getPoint());
    }

    @Test
    @DisplayName("충전 금액은 1이상이여야 한다")
    void charge_over_zero_throwsException(){
        // given
        Long invalidPoint = -50L;

        // then
        assertThrows(IllegalArgumentException.class, () -> new UserPoint(1L, invalidPoint, LocalDateTime.now())); // 이 코드 블록이 예외를 던지는가?
    }

    @Test
    @DisplayName("충전 금액이 0 이하이면 예외를 던진다")
    void charge_withNonPositiveAmount_throwsException(){
        // given
        UserPoint userPoint = new UserPoint(1L, 300L, LocalDateTime.now());

        // when
//        userPoint.charge(-100L); // -> 여기서 예외 발생하면 밑에까지 가지 않으므로 테스트 자체가 중단됨

        // then
        assertThrows(IllegalArgumentException.class, () -> userPoint.charge(0L)); // 이 코드 블록이 예외를 던지는가?
        assertThrows(IllegalArgumentException.class, () -> userPoint.charge(-10L));
    }

    @Test
    @DisplayName("충전 금액이 최대 허용치를 초과하면 예외를 던진다")
    void charge_withTooLargeAmount_throwsException(){
        // given
        UserPoint userPoint = new UserPoint(1L, 300L, LocalDateTime.now());

        // when + then
        assertThrows(IllegalArgumentException.class, () -> userPoint.charge(15000L)); // 이 코드 블록이 예외를 던지는가?
    }

    @Test
    @DisplayName("사용 금액은 0보다 크지 않으면 예외를 던진다")
    void use_withNonPositiveAmount_throwsException(){
        // given
        UserPoint userPoint = new UserPoint(1L, 300L, LocalDateTime.now());

        // when + then
        assertThrows(IllegalArgumentException.class, () -> userPoint.use(-10L));
    }

    @Test
    @DisplayName("포인트가 부족하면 예외를 던진다")
    void use_notEnough_throwsException(){
        // given
        UserPoint userPoint = new UserPoint(1L, 300L, LocalDateTime.now());

        // when + then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> userPoint.use(500L)); // 이 코드 블록이 예외를 던지는가?
        assertEquals("포인트가 부족합니다", exception.getMessage());
    }
}
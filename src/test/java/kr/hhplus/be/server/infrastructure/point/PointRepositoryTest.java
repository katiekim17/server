package kr.hhplus.be.server.infrastructure.point;

import kr.hhplus.be.server.domain.model.UserPoint;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EntityScan("kr.hhplus.be.server.domain.model") // 엔티티 위치 지정
@Slf4j
class PointRepositoryTest {

    @Autowired
    PointRepository pointRepository;

    @Test
    void saveAndFindByUserId() {

        //given
        UserPoint point = new UserPoint(100L, LocalDateTime.now());
        pointRepository.save(point);

        Optional<UserPoint> result = pointRepository.findByUserId(point.getUserId());
        assertThat(result).isPresent();
    }

}
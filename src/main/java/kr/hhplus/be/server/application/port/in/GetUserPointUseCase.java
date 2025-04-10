package kr.hhplus.be.server.application.port.in;

import kr.hhplus.be.server.domain.model.PointHistory;
import kr.hhplus.be.server.domain.model.UserPoint;

import java.util.List;

//  사용자가 하고싶은 일(유즈케이스)를 부르는 문
public interface GetUserPointUseCase {
    UserPoint getUserPoint(long userId); // 특정 유저의 포인트를 조회하는 기능
    List<PointHistory> getPointHistory(long id); // 특정 유저의 포인트 충전/이용 내역을 조회하는 기능
    UserPoint setPointCharge(long id, long amount); // 특정 유저의 포인트를 충전하는 기능
    UserPoint setPointUse(long id, long amount);  // 특정 유저의 포인트를 사용, 유저 정보에 값 입력, 포인트 사용 기록에도 저장
}

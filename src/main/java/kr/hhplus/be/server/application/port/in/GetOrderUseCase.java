package kr.hhplus.be.server.application.port.in;


import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.Order;
import kr.hhplus.be.server.domain.model.OrderItem;

import java.util.List;

//- 사용자 식별자와 (상품 ID, 수량) 목록을 입력받아 주문하고 결제를 수행하는 API 를 작성합니다.
//- 결제는 기 충전된 잔액을 기반으로 수행하며 성공할 시 잔액을 차감해야 합니다.
//- 데이터 분석을 위해 결제 성공 시에 실시간으로 주문 정보를 데이터 플랫폼에 전송해야 합니다. ( 데이터 플랫폼이 어플리케이션 `외부` 라는 가정만 지켜 작업해 주시면 됩니다 )
public interface GetOrderUseCase {

//    Order verifyOrder(Long userId, List<OrderItem> items);     // 임시 재고 수량 계산

    // 쿠폰 넣은 최종 상품 값
//    List<Order> useCoupon(Long userId, Order order);
//
//
//    Order placeOrder(Long userId, Order items);
//    List<Coupon> searchMyCoupons(Long userId); // 쿠폰 조회 요청, 사용자 쿠폰 테이블에서 쿠폰조회 요청 ->사용자가 가지고 있는 쿠폰 리스트 받음
//
//    void payOrder(Long userId, Long orderId);


    // 재고 테이블에 해당 상품의 임시재고 수량의 데이터를 변경

    // 최종 결제 금액 계산 요청, 계산
    // 결제 방법 지정 및 결제 요청
    // 주문 테이블에서 주문상태 완료로 변경
    // 재고 테이블에서 임지 재고 수량 계산, 확정재고 수량 변경
    // 결제 상태값 요청
    // 결제 완료 보냄

    // 재고 부족시 임시 주문테이블 확인 후 부족 메세지 전송
}

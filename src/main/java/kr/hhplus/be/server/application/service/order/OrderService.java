package kr.hhplus.be.server.application.service.order;

import kr.hhplus.be.server.application.port.in.GetOrderUseCase;
import kr.hhplus.be.server.application.port.out.CouponPort;
import kr.hhplus.be.server.application.port.out.InventoryPort;
import kr.hhplus.be.server.application.port.out.OrderPort;
import kr.hhplus.be.server.application.port.out.PaymentPort;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.Inventory;
import kr.hhplus.be.server.domain.model.Order;
import kr.hhplus.be.server.domain.model.OrderItem;
import kr.hhplus.be.server.domain.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements GetOrderUseCase {

    private final OrderPort orderPort;
    private final InventoryPort inventoryPort;
    private final CouponPort couponPort;
    private final PaymentPort paymentPort;

    public OrderService(OrderPort orderPort, InventoryPort inventoryPort, CouponPort couponPort, PaymentPort paymentPort) {
        this.orderPort = orderPort;
        this.inventoryPort = inventoryPort;
        this.couponPort = couponPort;
        this.paymentPort = paymentPort;
    }

    // 쿠폰 조회 요청
    // 사용자 쿠폰 테이블에서 쿠폰조회 요청 ->사용자가 가지고 있는 쿠폰 리스트 받음
    // 최종 결제 금액 계산 요청, 계산
    // 결제 방법 지정 및 결제 요청
    // 주문 테이블에서 주문상태 완료로 변경
    // 재고 테이블에서 임지 재고 수량 계산, 확정재고 수량 변경
    // 결제 상태값 요청
    // 결제 완료 보냄

    @Override
    public Order verifyOrder(Long userId, List<OrderItem> items) {

        // 주문하기버튼 혹은 장바구니에서 주문하기 누르면 해당 제품들이 있는 지 디비에서 조회하여 확인한다
        List<Long> insufficientItems = new ArrayList<>();
        for (OrderItem item : items) {
            Inventory inventory = inventoryPort.getInventory(item.getProductId());
            if (!inventory.hasStock(item.getQuantity())) {
                insufficientItems.add(item.getProductId());
            }
        }
        // 주문하려는 상품들중 1개의 상품이라도 재고가 부족하다면 주문다시 해달라고 요청
        if (!insufficientItems.isEmpty()) {
            throw new IllegalStateException("재고 부족 상품: " + insufficientItems); // "message": "재고 부족 상품: [1001, 1002]"
        }

        // 재고가 있다면, 주문테이블에 저장.(상태:임시)
        for (OrderItem item : items) {
            inventoryPort.reserveTempStock(userId, item.getProductId(), item.getQuantity(), LocalDateTime.now());
        }

        // 계산하기
        // 주문테이블에 임시로 저장
        Order order = new Order(0L, userId, items, 0, OrderStatus.READY, LocalDateTime.now());
        int totalPrice = order.calTotalPrice(items);
        order.updateTotalPrice(totalPrice);
        return orderPort.save(order);
    }


    // 쿠폰 조회
    @Override
    public List<Coupon> searchMyCoupon(Long userId){

        List<Coupon> couponList = new ArrayList<>();
        // 사용가능한 쿠폰 버튼을 누르면 디비에서 사용가능한 쿠폰목록을 디비에서 조회해사 가져온다

        Coupon coupon = couponPort.getAvailableCoupons(userId);
        couponList.add(coupon);

        return couponList;
    }

    // 쿠폰 넣은 최종 상품 값계산
    @Override
    public List<Order> useCoupon(Long userId, Order order){

        List<Coupon> couponList = searchMyCoupon(userId);

        // 주문페이지에서 주문할 상품 정보를 가져온다
        // 디비에서 주문정보를 조회한다.


        // 적용 가능한 쿠폰을 확인해서 최종값 계산하기
        List<Order> orderList = new ArrayList<>();

        return orderList;
    }


    // TODO 주문하기
    @Override
    public Order placeOrder(Long userId, Order order) {

        // 주문페이지(상세)에서 주문수량, 배송주소, 결제방법등의 정보가 넘어온다
        // 디비에서 주문테이블에 주문이 있는지 확인
        Order confirmedOrder = orderPort.existsPendingOrder(userId, order.getOrderId());
        if (confirmedOrder == null){
            throw new IllegalStateException("존재하지 않는 주문입니다. 다시 주문해주세요.");
        }

        // 주문 테이블에 주문정보 갱신
        order = confirmedOrder.checkOrder(confirmedOrder, order);

        confirmedOrder = orderPort.save(order);

        // 임시 재고 수량 계산
        // 재고 테이블에 해당 상품의 임시재고 수량의 데이터를 변경
        for (OrderItem item : order.getItems()) {
            inventoryPort.confirmStock(item.getProductId(), item.getQuantity());
        }


        // 계산 실행
        payOrder(userId, confirmedOrder.getOrderId());
//        List<String> coupons = couponPort.getAvailableCoupons(userId);
//        if (!coupons.isEmpty()) {
//            totalPrice -= 1000; // 쿠폰 할인 예시
//        }
//
//
//        for (OrderItem item : items) {
//            inventoryPort.reserveTempStock(item.getProductId(), item.getQuantity());
//        }
//
//        boolean paid = paymentPort.pay(userId, totalPrice);
//        if (!paid || !paymentPort.isPaymentConfirmed(userId)) {
//            throw new IllegalStateException("결제 실패");
//        }
//
//        orderPort.markCompleted(saved.getOrderId());
//
//        for (OrderItem item : items) {
//            inventoryPort.confirmStock(item.getProductId(), item.getQuantity());
//        }

        return confirmedOrder;
    }

    @Override
    public void payOrder(Long userId, Long orderId) {

    }
}

package kr.hhplus.be.server.domain.model;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.type.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "order")
public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long userId;
//    @OneToMany
    private List<OrderItem> items;
    private int totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order(Long orderId, Long userId, List<OrderItem> items, int totalPrice, OrderStatus status, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Order(Long userId, List<OrderItem> items, int totalPrice, OrderStatus status, LocalDateTime createdAt) {
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }


    public void complete() {
        this.status = OrderStatus.COMPLETED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }

    public void updateTotalPrice(int newPrice) {
        this.totalPrice = newPrice;
    }

    public int calTotalPrice(List<OrderItem> orderItem){
        int totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.getQuantity() * item.getUnitPrice();
        }
        return totalPrice;
    }


//    public Order checkOrder(Order confirmedOrder, Order order) {
//
//        if(!confirmedOrder.getOrderId().equals(order.getUserId())
//            || !confirmedOrder.getItems().equals(order.getItems())
//
//        ){
//            throw new IllegalStateException("존재하지 않는 주문입니다. 다시 주문해주세요.");
//        }
//
//        order.complete(); // 상태변경
//        return order;
//    }
}

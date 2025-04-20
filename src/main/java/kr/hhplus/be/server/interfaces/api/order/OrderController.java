package kr.hhplus.be.server.interfaces.api.order;

import kr.hhplus.be.server.application.port.in.GetOrderUseCase;
import kr.hhplus.be.server.domain.model.Coupon;
import kr.hhplus.be.server.domain.model.Order;
import kr.hhplus.be.server.domain.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController implements GetOrderUseCase {

    private final GetOrderUseCase getOrderUseCase;
    
    @Override
    public Order verifyOrder(Long userId, List<OrderItem> items) {
        Order order = getOrderUseCase.verifyOrder(userId, items);
        return null;
//        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @Override
    public List<Order> useCoupon(Long userId, Order order) {
        return List.of();
    }

    @Override
    public Order placeOrder(Long userId, Order items) {
        return null;
    }

    @Override
    public List<Coupon> searchMyCoupons(Long userId) {
        return List.of();
    }

    @Override
    public void payOrder(Long userId, Long orderId) {

    }
}

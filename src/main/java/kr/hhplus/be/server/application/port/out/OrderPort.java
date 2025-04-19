package kr.hhplus.be.server.application.port.out;

import kr.hhplus.be.server.domain.model.Order;

public interface OrderPort {
    Order save(Order order);
    Order existsPendingOrder(Long userId, Long orderId);
    void markCompleted(Long orderId);
}

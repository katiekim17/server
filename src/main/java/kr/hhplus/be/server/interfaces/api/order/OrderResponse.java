package kr.hhplus.be.server.interfaces.api.order;

import kr.hhplus.be.server.domain.model.Order;


// DTO 용도
public record OrderResponse(Long userId, Long oders) {
    public static kr.hhplus.be.server.interfaces.api.order.OrderResponse from(Order order) {
        return new kr.hhplus.be.server.interfaces.api.order.OrderResponse(order);
    }
}
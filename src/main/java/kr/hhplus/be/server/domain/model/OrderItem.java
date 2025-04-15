package kr.hhplus.be.server.domain.model;

import lombok.Getter;

@Getter
public class OrderItem {
    private final Long productId;
    private final int quantity;
    private final int unitPrice;

    public OrderItem(Long productId, int quantity, int unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getSubtotal() {
        return quantity * unitPrice;
    }

}

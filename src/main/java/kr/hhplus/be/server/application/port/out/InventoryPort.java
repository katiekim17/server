package kr.hhplus.be.server.application.port.out;

import kr.hhplus.be.server.domain.model.Inventory;
import kr.hhplus.be.server.domain.model.OrderItem;

import java.time.LocalDateTime;

//private final Long productId;
//private int totalStock; // 원래 가지고 있는 상품 전체 갯수
//private int reservedStock; // 예약 재고
//private int confirmedStock; // 확정 재고

public interface InventoryPort {
    Inventory getInventory(Long productId);
    void reserveTempStock(Long userId, OrderItem item, LocalDateTime now); // 주문정보 임시로 저장(장바구니, 혹은 주문 완료전 임시저장)
    void confirmStock(Long productId, int quantity);

    Inventory checkInventory(Long productId);

    void save(Inventory inventory);
}
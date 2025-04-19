package kr.hhplus.be.server.infrastructure.inventory;

import kr.hhplus.be.server.domain.model.Inventory;
import kr.hhplus.be.server.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findAllByProductId(Long productId);
    void reserveTempStock(Long userId, OrderItem item, LocalDateTime now);
//    List<Inventory> findInventoryByProductIds(List<Long> productIds);
//    List<PointHistory> findByUserId(Long userId);
//    List<PointHistory> loadPointHistory(Long userId);
}

package kr.hhplus.be.server.infrastructure.inventory;

import kr.hhplus.be.server.application.port.out.InventoryPort;
import kr.hhplus.be.server.domain.model.Inventory;
import kr.hhplus.be.server.domain.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class InventoryAdapter implements InventoryPort {

    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory getInventory(Long productId) {
        return inventoryRepository.findAllByProductId(productId);
    }

    @Override
    public void reserveTempStock(Long userId, OrderItem item, LocalDateTime now) {
        inventoryRepository.save(userId, item);
    }

    @Override
    public void confirmStock(Long productId, int quantity) {

    }

    @Override
    public Inventory checkInventory(Long productId) {
        return null;
    }

    @Override
    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}

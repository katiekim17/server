package kr.hhplus.be.server.interfaces.persistence.entity.product;

import jakarta.persistence.Entity;
import kr.hhplus.be.server.domain.model.Product;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class ProductEntity {

    @Id
    private Long productId;
    private String name;
    private int price;
    private Long registerId;
    private int category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product toDomain() {
        return new Product(productId, name, price, registerId, category, createdAt, updatedAt);
    }

    public static ProductEntity from(Product product) {
        ProductEntity e = new ProductEntity();
        e.productId = product.getProductId();
        e.name = product.getName();
        e.price = product.getPrice();
        e.registerId = product.getRegisterId();
        e.category = product.getCategory();
        e.createdAt = product.getCreatedAt();
        e.updatedAt = product.getUpdatedAt();
        return e;
    }
}

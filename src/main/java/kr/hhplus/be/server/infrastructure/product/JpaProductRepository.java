package kr.hhplus.be.server.infrastructure.product;

import kr.hhplus.be.server.interfaces.persistence.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByName(String name);
}

package kr.hhplus.be.server.infrastructure.product;

import kr.hhplus.be.server.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAllByName(String name);
//    List<PointHistory> findByUserId(Long userId);
//    List<PointHistory> loadPointHistory(Long userId);
}


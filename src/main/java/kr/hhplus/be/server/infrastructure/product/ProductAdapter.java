package kr.hhplus.be.server.infrastructure.product;

import kr.hhplus.be.server.application.port.out.ProductPort;
import kr.hhplus.be.server.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;

//    @Override
//    public Product loadById(Long productId) {
//        return null;
//    }
//
//    @Override
//    public List<Product> loadByName(String name) {
//        return List.of();
//    }

//    @Override
//    public Product loadById(Long productId) {
//        return jpa.findById(productId)
//                .map(ProductEntity::toDomain)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품 없음"));
//    }
//
//    @Override
//    public List<Product> loadByName(String name) {
//        return jpa.findAllByName(name).stream()
//                .map(ProductEntity::toDomain)
//                .collect(Collectors.toList());
//    }
}

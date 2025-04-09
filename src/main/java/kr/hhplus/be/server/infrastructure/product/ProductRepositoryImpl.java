package kr.hhplus.be.server.infrastructure.product;

import kr.hhplus.be.server.application.port.out.LoadProductPort;
import kr.hhplus.be.server.domain.model.Product;
import kr.hhplus.be.server.interfaces.persistence.entity.product.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements LoadProductPort {

    private final JpaProductRepository jpa;


    @Override
    public Product loadById(Long productId) {
        return jpa.findById(productId)
                .map(ProductEntity::toDomain)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품 없음"));
    }

    @Override
    public List<Product> loadByName(String name) {
        return jpa.findAllByName(name).stream()
                .map(ProductEntity::toDomain)
                .collect(Collectors.toList());
    }
}

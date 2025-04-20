package kr.hhplus.be.server.interfaces.api.product;

import kr.hhplus.be.server.application.port.in.GetProductUseCase;
import kr.hhplus.be.server.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final GetProductUseCase getProductUseCase;

    @Override
    public ResponseEntity<ProductResponse> getProductById(Long id) {
        Product product = getProductUseCase.getProductById(id);
        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProductByName(String name) {
        List<Product> products = getProductUseCase.getProductByName(name);
        return ResponseEntity.ok(
                products.stream().map(ProductResponse::from).toList()
        );
    }
}


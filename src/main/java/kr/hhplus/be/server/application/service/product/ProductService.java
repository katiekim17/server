package kr.hhplus.be.server.application.service.product;

import kr.hhplus.be.server.application.port.in.GetProductUseCase;
import kr.hhplus.be.server.application.port.out.ProductPort;
import kr.hhplus.be.server.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService implements GetProductUseCase {

    private final ProductPort productPort;

    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }
//
//    @Override
//    public Product getProductById(Long productId) {
//        return productPort.loadById(productId);
//    }
//
//    @Override
//    public List<Product> getProductByName(String name) {
//        return productPort.loadByName(name);
//    }
}

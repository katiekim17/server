package kr.hhplus.be.server.application.service.product;

import kr.hhplus.be.server.application.port.in.GetProductUseCase;
import kr.hhplus.be.server.application.port.out.LoadProductPort;
import kr.hhplus.be.server.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService implements GetProductUseCase {

    private final LoadProductPort loadProductPort;

    public ProductService(LoadProductPort loadProductPort) {
        this.loadProductPort = loadProductPort;
    }

    @Override
    public Product getProductById(Long productId) {
        return loadProductPort.loadById(productId);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return loadProductPort.loadByName(name);
    }
}

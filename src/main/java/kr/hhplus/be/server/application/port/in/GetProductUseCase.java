package kr.hhplus.be.server.application.port.in;

import kr.hhplus.be.server.domain.model.Product;

import java.util.List;

//  "야, 상품 조회 기능 좀 써보자!" → 사용자가 하고싶은 일(유즈케이스)를 부르는 문
public interface GetProductUseCase {
    Product getProductById(Long productId); // 상품 ID로 상품을 조회한다
    List<Product> getProductByName(String name); // 상품 이름으로 상품을 조회한다
}

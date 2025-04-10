package kr.hhplus.be.server.application.port.out;

import kr.hhplus.be.server.domain.model.Product;

import java.util.List;

//"상품 정보 어디서 불러오지?" → DB나 외부 시스템을 쓰는 문
public interface LoadProductPort {
    Product loadById(Long productId);                     // 단건 조회 (아이디)
    List<Product> loadByName(String name);                // 이름으로 조회 (이름)
//    List<Product> loadByCategory(ProductCategory category); // 카테고리로 조회
//    List<Product> loadTopItems(int limit);
}
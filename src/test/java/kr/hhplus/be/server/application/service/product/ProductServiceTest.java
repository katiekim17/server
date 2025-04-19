package kr.hhplus.be.server.application.service.product;

import kr.hhplus.be.server.application.port.out.ProductPort;
import kr.hhplus.be.server.domain.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    //가짜 객체가져오기
    @Mock ProductPort productPort;
//    @InjectMocks GetProductUseCase getProductUseCase;
    @InjectMocks ProductService getProductService;

    final long ANY_ID = 1L;
    final int category = 5;
    final LocalDateTime createdAt = now();
//    final LocalDateTime updatedAt = now();

    @Test
    @DisplayName("아이디를 가지고 상품을 정상적으로 조회한다")
    void getProductById() {

        // given
        Product product = new Product(ANY_ID, "운동화", 10000, 99L, 1, now(), now());
        when(productPort.loadById(ANY_ID)).thenReturn(product);

        // when
        Product result = getProductService.getProductById(ANY_ID);

        // then
        assertEquals("운동화", result.getName());
        assertEquals(10000, result.getPrice());

    }

    @Test
    @DisplayName("이름을 가지고 상품을 정상적으로 조회한다")
    void getProductByName() {
        // given
        List<Product> mockResult = new ArrayList<>();
        Product product = new Product(ANY_ID, "운동화", 10000, 99L, 1, now(), now());
        mockResult.add(product);
        when(productPort.loadByName("운동화")).thenReturn(mockResult);

        // when
        List<Product> result = getProductService.getProductByName("운동화");

        // then
        assertEquals(1, result.size());
        assertEquals("운동화", result.get(0).getName());
        assertEquals(10000, result.get(0).getPrice());

    }
}
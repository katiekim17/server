//package kr.hhplus.be.server.interfaces.api.product;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Tag(name = "상품 API")
//public interface ProductApi {
//
//    @Operation(summary = "상품 단건 조회")
//    @GetMapping("/{id}")
//    ResponseEntity<ProductResponse> getProductById(@PathVariable Long id);
//
//    @Operation(summary = "상품 이름으로 조회")
//    @GetMapping("/search")
//    ResponseEntity<List<ProductResponse>> getProductByName(@RequestParam String name);
//}
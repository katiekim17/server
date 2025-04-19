//package kr.hhplus.be.server.interfaces.api.product;
//
//import kr.hhplus.be.server.domain.model.Product;
//
//public record ProductResponse(Long productId, String name, int price) {
//    public static ProductResponse from(Product p) {
//        return new ProductResponse(p.getProductId(), p.getName(), p.getPrice());
//    }
//}

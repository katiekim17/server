//package kr.hhplus.be.server.application.service.order;
//
//import jakarta.transaction.Transactional;
//import kr.hhplus.be.server.application.port.out.CouponPort;
//import kr.hhplus.be.server.application.port.out.InventoryPort;
//import kr.hhplus.be.server.application.port.out.OrderPort;
//import kr.hhplus.be.server.application.port.out.PaymentPort;
//import kr.hhplus.be.server.domain.model.Coupon;
//import kr.hhplus.be.server.domain.model.Inventory;
//import kr.hhplus.be.server.domain.model.Order;
//import kr.hhplus.be.server.domain.model.OrderItem;
//import kr.hhplus.be.server.domain.type.DiscountType;
//import kr.hhplus.be.server.domain.type.OrderStatus;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional
//public class OrderServiceIntegrationTest {
//
//    @Autowired
//    private OrderService orderService;
//
////    @Autowired
////    private OrderPort orderPort;
//
//    @Autowired
//    private InventoryPort inventoryPort;
//
//    @Autowired
//    private CouponPort couponPort;
//
//    @Autowired
//    private PaymentPort paymentPort;
//
//    static final long ANY_USER_ID = 1L; // 아이디
//
//    @BeforeEach
//    void setUp() {
//        // 테스트용 재고 데이터 삽입 (재고 충분하게)
////        inventoryPort.save(new Inventory(1L, 10, 0, 0));
////        inventoryPort.save(new Inventory(2L, 10, 0, 0));
////        inventoryPort.save(new Inventory(3L, 10, 0, 0));
////        inventoryPort.save(new Inventory(4L, 10, 0, 0));
////        inventoryPort.save(new Inventory(5L, 10, 0, 0));
//
////        couponPort.save(new Coupon("POP", DiscountType.PERCENTAGE, new BigDecimal("10.0"), new BigDecimal("10000"),
////                        new BigDecimal("5000"), LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7), true, LocalDateTime.now(), LocalDateTime.now()));
////        couponPort.save(new Coupon("K-POP", DiscountType.PERCENTAGE, new BigDecimal("10.0"), new BigDecimal("20000"),
////                        new BigDecimal("5000"), LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7), true, LocalDateTime.now(), LocalDateTime.now()));
////        couponPort.save(new Coupon("BALLAD", DiscountType.PERCENTAGE, new BigDecimal("10.0"), new BigDecimal("30000"),
////                        new BigDecimal("5000"), LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7), true, LocalDateTime.now(), LocalDateTime.now()));
////        couponPort.save(new Coupon("HIP-POP", DiscountType.PERCENTAGE, new BigDecimal("10.0"), new BigDecimal("25000"),
////                        new BigDecimal("5000"), LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7), true, LocalDateTime.now(), LocalDateTime.now()));
////        couponPort.save(new Coupon("CLASSIC", DiscountType.PERCENTAGE, new BigDecimal("10.0"), new BigDecimal("18000"),
////                        new BigDecimal("5000"), LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7), true, LocalDateTime.now(), LocalDateTime.now()));
//    }
//
//
////    @Test
////    @DisplayName("재고가 충분한 경우 주문 임시 생성 성공")
////    void verifyOrder() {
////
////        // given - 아이템 3개를 가진 주문을 넣는다
////        List<OrderItem> orderItems = List.of(
////                new OrderItem(1L, 1, 3500),
////                new OrderItem(2L, 1, 4500),
////                new OrderItem(3L, 1, 5000)
////        );
////
////        // when
////        Order order = orderService.verifyOrder(ANY_USER_ID, orderItems);
////
////        // then
////        Assertions.assertNotNull(order);
////        Assertions.assertEquals(OrderStatus.READY, order.getStatus());
////        Assertions.assertEquals(3, order.getItems().size());
////        Assertions.assertEquals(13000, order.getTotalPrice());
////
////    }
//
////    @Test
////    @DisplayName("유저가 보유한 쿠폰 조회한다")
////    void searchMyCoupon(){
////
////        // given - 유저가 보유한 쿠폰 리스트가 있어야 함.
////
////
////        // when
////        List<Coupon> couponList = orderService.searchMyCoupons(ANY_USER_ID);
//////                searchMyCoupon(ANY_USER_ID);
////
////        // then
//////      Assertions.assertNotNull(couponList);
////
////
////    }
//
//}

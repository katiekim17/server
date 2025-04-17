package kr.hhplus.be.server.infrastructure.payment;

import kr.hhplus.be.server.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
//    List<PointHistory> findByUserId(Long userId);
//    List<PointHistory> loadPointHistory(Long userId);
}

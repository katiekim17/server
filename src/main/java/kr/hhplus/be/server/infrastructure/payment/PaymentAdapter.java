package kr.hhplus.be.server.infrastructure.payment;

import kr.hhplus.be.server.application.port.out.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {

    private final PaymentRepository paymentRepository;

//    @Override
//    public boolean pay(Long userId, int amount) {
//        return false;
//    }
//
//    @Override
//    public boolean isPaymentConfirmed(Long userId) {
//        return false;
//    }
}
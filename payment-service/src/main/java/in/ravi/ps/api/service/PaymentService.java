package in.ravi.ps.api.service;

import in.ravi.ps.api.entity.Payment;
import in.ravi.ps.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment) {
        payment.setPaymentStatus(getRandomPaymentStatus());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public String getRandomPaymentStatus() {
        //In real time, this logic should be a REST call to payment engine like paypal, paytm etc....
        return new Random().nextBoolean() ? "Success" : "Failure";
    }
}

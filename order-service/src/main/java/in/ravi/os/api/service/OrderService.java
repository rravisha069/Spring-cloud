package in.ravi.os.api.service;

import in.ravi.os.api.common.Payment;
import in.ravi.os.api.common.TransactionResponse;
import in.ravi.os.api.configuration.PaymentFromConfigServer;
import in.ravi.os.api.entity.Order;
import in.ravi.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

//    @Autowired
//    private PaymentFromConfigServer paymentFromConfigServer;

    @Value("${microservice.paymentService}")
    private String paymentServiceUri;

    public TransactionResponse bookOrder(Order order) {
        Payment payment = new Payment();
        payment.setOrderId(order.getOrderId());
        payment.setAmount(order.getPrice());
        //Rest call to do Payment
        Payment paymentResponse = restTemplate.postForObject(paymentServiceUri, payment, Payment.class);
        return new TransactionResponse(orderRepository.save(order),
                                       paymentResponse.getAmount(),
                                       paymentResponse.getTransactionId(),
                                       buildMessage(paymentResponse.getPaymentStatus())
                                       );
    }

    private String buildMessage(String paymentStatus) {
        if(paymentStatus.equals("Failure")) {
            return "Payment processing failed and order has been saved in cart";
        } else {
            return "Payment processing Successful and order has been placed";
        }
    }
}

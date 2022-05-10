package in.ravi.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> publishOrderServiceFallBack() {
        return Mono.just("Order service is taking too long to respond or down at the movement. Please try again later");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String> publishPaymentServiceFallBack() {
        return Mono.just("Payment service is taking too long to respond or down at the movement. Please try again later");
    }
}

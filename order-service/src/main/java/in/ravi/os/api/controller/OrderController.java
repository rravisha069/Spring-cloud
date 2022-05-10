package in.ravi.os.api.controller;

import in.ravi.os.api.common.TransactionResponse;
import in.ravi.os.api.entity.Order;
import in.ravi.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/bookOrder")
    public TransactionResponse bookOrder(@RequestBody Order order) {
        return orderService.bookOrder(order);
    }
}

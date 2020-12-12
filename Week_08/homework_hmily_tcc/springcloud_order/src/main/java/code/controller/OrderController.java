package code.controller;

import code.entity.Order;
import code.service.OrderService;
import code.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * order controler.
 *
 * @author wangwenjie
 */
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/pay")
    public String pay(@RequestBody Order order){
        orderService.pay(order);
        return "ok";
    }
}

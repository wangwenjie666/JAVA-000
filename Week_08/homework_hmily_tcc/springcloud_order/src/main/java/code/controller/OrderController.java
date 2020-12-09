package code.controller;

import code.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * order controler.
 *
 * @author wangwenjie
 */
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/pay")
    public String pay(){
        orderService.pay();
        return "ok";
    }
}

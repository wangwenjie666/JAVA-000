package code.controller;

import code.entity.Order;
import code.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @author wangwenjie
 * @date 2020-12-08
 */
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add")
    public String add(Long userId, String phone, String product) {
        Order order = new Order();
        order.setUserId(userId);
        order.setPhone(phone);
        order.setProduct(product);
        orderService.save(order);
        return "ok";
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/update")
    public String update(Long orderId) {
        orderService.update(orderId);
        return "ok";
    }

    @GetMapping("/delete")
    public String delete(Long orderId) {
        orderService.delete(orderId);
        return "ok";
    }
}

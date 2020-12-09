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

    //雪花算法均匀分配 2^n -1
    public static void main(String[] args) {
        System.out.println(543559159203758081L % 15);
        System.out.println(543559506479546368L % 15);
        System.out.println(543559507670728705L % 15);
        System.out.println(543559813204803584L % 15);
        System.out.println(543559814219825153L % 15);
        System.out.println(543559814844776448L % 15);
        System.out.println(543559815364870145L % 15);
        System.out.println(543559815872380928L % 15);
        System.out.println(543559816501526529L % 15);
    }
}

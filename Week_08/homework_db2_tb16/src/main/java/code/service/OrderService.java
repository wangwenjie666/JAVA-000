package code.service;

import code.entity.Order;
import code.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * orderserivce
 *
 * @author wangwenjie
 * @date 2020-12-08
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void update(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        order.setPhone("update phone");
        orderRepository.save(order);
    }

    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}

package code.repository;

import code.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * order
 *
 * @author wangwenjie
 * @date 2020-12-08
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}

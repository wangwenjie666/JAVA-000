package code.mapper;

import code.entity.Order;
import org.apache.ibatis.annotations.Insert;

/**
 * order mapper.
 *
 * @author wangwenjie
 * @date 2020-12-10
 */
public interface OrderMapper {

    @Insert("insert into t_order(status) values(#{status})")
    void saveOrder(Order order);
}

package code.mapper;

import code.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * order mapper.
 *
 * @author wangwenjie
 * @date 2020-12-10
 */
public interface OrderMapper {

    @Insert("insert into t_order(id,status) values(#{id},#{status})")
    void saveOrder(Order order);

    @Update("update t_order set status = #{status} where id = #{id}")
    void updateOrder(Order order);
}

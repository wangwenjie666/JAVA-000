package code.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @author wangwenjie
 * @date 2020-12-08
 */
@Data
@Entity
@Table(name = "t_order")
public class Order {
    //订单id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    //用户
    @Column(name = "user_id")
    private Long userId;
    //电话
    private String phone;
    //商品
    private String product;

}


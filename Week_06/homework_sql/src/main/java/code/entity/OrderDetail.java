package code.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情表
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
@Entity
@Table(name = "t_order_detail")
@Data
public class OrderDetail extends BaseEntity implements Serializable {

    //订单详情主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    //当前商品下单金额
    private BigDecimal price;

    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @OneToOne
    private Product product;

    //订单id
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne
    private Order order;
}

package code.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
@Entity
@Table(name = "t_order")
@Data
public class Order extends BaseEntity implements Serializable {

    //订单id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    //订单编号
    private String orderNo;

    //地址
    private String address;

    //订单总价
    private BigDecimal priceAmount;
    //优惠金额
    private BigDecimal districtPrice;
    //运费
    private BigDecimal shippingPrice;
    //快递公司
    private String shippingCompany;
    //快递单号
    private String shippingNo;
    //发货时间
    private Date shippingDate;
    //订单状态
    private String validStatus;

    //用户id
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

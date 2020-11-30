package code.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 简化订单表
 *
 * @author wangwenjie
 * @date 2020-11-30
 */
@Data
public class Order {
    //订单id
    private Long orderId;
    //用户
    private String phone;
    //商品
    private String product;
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
}

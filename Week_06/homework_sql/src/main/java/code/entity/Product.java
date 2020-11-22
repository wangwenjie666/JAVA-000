package code.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
@Entity
@Table(name = "t_product")
@Data
public class Product extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //库存
    private Long productCount;
}

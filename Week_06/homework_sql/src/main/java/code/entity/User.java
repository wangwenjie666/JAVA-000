package code.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
@Entity
@Table(name = "t_user")
@Data
public class User extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String phone;
    //身份证号
    private String idCard;
    //银行卡号
    private String backCard;
}

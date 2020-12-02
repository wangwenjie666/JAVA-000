package code.entity;

import lombok.Data;

/**
 * user表
 *
 * @author wangwenjie
 * @date 2020-12-01
 */
@Data
public class User {
    //主键
    private Integer id;
    //用户名
    private String username;
    //区分主从数据源字段
    private String dbsource;
}

package code.mapper;

import code.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-12-03
 */
@Mapper
public interface UserMapper {
    /**
     * 插入一条记录
     *
     * @return 更新条目数
     */
    int insert(User user);

    /**
     * 获取所有用户
     */
    List<User> selectAll();
}

package code.dao;

import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-12-01
 */
@Repository
public interface UserDao {

    void save(String sql);
}

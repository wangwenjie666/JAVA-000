package code.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * userSerivce
 *
 * @author wangwenjie
 * @date 2020-12-01
 */
@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * insert into master & slave
     */
    public void insertUser(final String name, final String source) {
        final String sql = "insert into t_user(username,dbsource) values (?, ?)";
        jdbcTemplate.update(sql, name, source);
    }

    /**
     * select from slave
     */
    public void selectUser() {
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from t_user order by id desc limit 1");
        System.out.println(map);
    }
}

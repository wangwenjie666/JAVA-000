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

    private final JdbcTemplate masterTemplate;
    private final JdbcTemplate slaveTemplate;

    public UserService(JdbcTemplate masterTemplate, JdbcTemplate slaveTemplate) {
        this.masterTemplate = masterTemplate;
        this.slaveTemplate = slaveTemplate;
    }

    /**
     * insert into master & slave
     */
    public void insertUser() {
        final String sql = "insert into t_user(username,dbsource) values ('zhangsan',?)";
        masterTemplate.update(sql, "master");
        slaveTemplate.update(sql, "slave");
    }

    /**
     * select from slave
     */
    public void selectUser(Integer id) {
        Map<String, Object> map = slaveTemplate.queryForMap("select * from t_user where id = " + id);
        System.out.println(map);
    }
}

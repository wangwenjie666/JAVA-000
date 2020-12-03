package code.service;

import code.entity.User;
import code.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * userSerivce
 *
 * @author wangwenjie
 * @date 2020-12-01
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * insert into master & slave
     */
    public void insertUser(final User user) {
        userMapper.insert(user);
    }

    /**
     * select from slave
     */
    public void selectUser() {
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }
}

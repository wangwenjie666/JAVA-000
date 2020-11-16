package code.component.xml.service;

import code.component.xml.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

/**
 * service
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
@Slf4j
public class UserService {

    private UserDao userDao;

    public void login() {
        log.info("==>UserService transfer Dao");
        userDao.login();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        System.out.println("==>Spring 装配 userDao");
        this.userDao = userDao;
    }
}

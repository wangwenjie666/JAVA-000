package code.component.xml.service;

import code.component.xml.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
@Slf4j
public class LoginService {

    private final UserDao userDao;

    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void login(){
        log.info("==>LoginService 调用");
        userDao.login();
    }
}

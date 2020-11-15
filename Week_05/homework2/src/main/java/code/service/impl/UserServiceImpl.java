package code.service.impl;

import code.service.UserService;

/**
 * 实现类
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
public class UserServiceImpl implements UserService {

    public void login() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("用户登录成功！");
    }
}

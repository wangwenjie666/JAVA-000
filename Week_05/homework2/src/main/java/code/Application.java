package code;

import code.service.UserService;
import code.service.impl.UserServiceImpl;
import code.service.proxy.UserServiceProxy;

/**
 * root
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
public class Application {
    public static void main(String[] args) throws Throwable {
        UserService userService = new UserServiceImpl();


        //直接调用invoke方法
//        Object login = new UserServiceProxy(userService).invoke(null, userService.getClass().getMethod("login", null), null);


        //获取代理对象
        UserService proxy = new UserServiceProxy(userService).getProxyObject();
        //调用代理类的login 实际上调用了实现了InvocationHandler接口类的invoke方法，执行结果和上面一样
        proxy.login();
    }
}

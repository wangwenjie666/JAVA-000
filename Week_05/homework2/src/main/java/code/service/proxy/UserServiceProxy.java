package code.service.proxy;

import code.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
public class UserServiceProxy implements InvocationHandler {

    //代理的目标对象
    private Object targetObject;

    public UserServiceProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理开始....");

        long startTime = System.currentTimeMillis();

        //目标方法执行
        Object invoke = method.invoke(targetObject, args);

        System.out.println("执行目标方法花费时间：" + (System.currentTimeMillis() - startTime));

        System.out.println("代理结束....");

        return invoke;
    }

    //获取代理对象方法
    public UserService getProxyObject() {
        return (UserService) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), new UserServiceProxy(targetObject));
    }
}

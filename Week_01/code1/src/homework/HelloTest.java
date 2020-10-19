package homework;

import java.io.*;
import java.lang.reflect.Method;

/**
 * hello作业
 *
 * @author wangwenjie
 * @date 2020-10-17
 */
public class HelloTest {
    public static void main(String[] args) throws Exception {
        //重写findClass方法，将Hello.class内容通过流加载到内存中生成Class
        Class<?> helloClass = new HelloClassLoader().findClass("Hello");
        Object obj = helloClass.newInstance();
        Method hello = helloClass.getMethod("hello");
        hello.invoke(obj);
    }




}

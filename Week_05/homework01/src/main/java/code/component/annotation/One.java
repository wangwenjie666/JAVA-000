package code.component.annotation;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 扫描该类 创建bean
 *  Spring扫描到有@Component注解的类，创建bean放到容器中
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
@Slf4j
@Component
public class One {
    private String name;
    private String phone;

    @Autowired
    private Two two;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Two getTwo() {
        return two;
    }

    public void setTwo(Two two) {
        this.two = two;
    }

    public static Logger getLog() {
        return log;
    }

    public void oneFn() {
        log.info("Spring Bean : One");
        two.twoFn();
    }


}

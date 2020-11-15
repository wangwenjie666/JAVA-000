package code.component.xml;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
@Slf4j
public class XmlBean {
    private String name;
    private String phone;

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

    public void xmlBeanFn() {
        log.info("Spring xml Bean : name = {},phone = {}", name, phone);
    }
}

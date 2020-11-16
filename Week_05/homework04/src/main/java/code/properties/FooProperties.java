package code.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
@ConfigurationProperties(prefix = "foo")
public class FooProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

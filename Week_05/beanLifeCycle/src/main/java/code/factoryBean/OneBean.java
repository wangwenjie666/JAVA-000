package code.factoryBean;

/**
 * one bean
 *
 * @author wangwenjie
 * @date 2020-11-27
 */
public class OneBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OneBean{" +
                "name='" + name + '\'' +
                '}';
    }
}

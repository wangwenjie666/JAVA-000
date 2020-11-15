package code.entity;

import java.util.List;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
public class OneConfiguration {
    private final String name;
    private final String phone;
    private final List<String> girlFriends;


    public OneConfiguration(final String name, final String phone, final List<String> girlFriends) {
        this.name = name;
        this.phone = phone;
        this.girlFriends = girlFriends;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getGirlFriends() {
        return girlFriends;
    }

    @Override
    public String toString() {
        return "OneConfiguration{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", girlFriends=" + girlFriends +
                '}';
    }
}

package code.component.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * two
 *
 * @author wangwenjie
 * @date 2020-11-15
 */
@Component
@Slf4j
public class Two {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = "two address";
    }

    public void twoFn(){
        log.info("Spring Bean: Two");
    }
}

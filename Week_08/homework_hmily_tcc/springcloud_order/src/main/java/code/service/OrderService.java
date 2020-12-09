package code.service;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

/**
 * order Service.
 *
 * @author wangwenjie
 */
@Service
public class OrderService {

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void pay() {

    }
}

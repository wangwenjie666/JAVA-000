package code.client;

import code.entity.Account;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * .
 *
 * @author wangwenjie
 * @date 2020-12-10
 */
@FeignClient("account-server")
public interface AccountClient {

    @PostMapping("/payAccount")
    @Hmily
    String payAccount(@RequestBody Account account);
}

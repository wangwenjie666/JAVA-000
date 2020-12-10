package code.controller;

import code.entity.Account;
import code.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * accountcontroller.
 *
 * @author wangwenjie
 * @date 2020-12-10
 */
@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/payAccount")
    public String payAccount(@RequestBody Account account){
        accountService.payAccount(account);
        return "ok";
    }
}

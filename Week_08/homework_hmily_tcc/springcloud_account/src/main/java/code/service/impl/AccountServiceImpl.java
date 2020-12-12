package code.service.impl;

import code.entity.Account;
import code.mapper.AccountMapper;
import code.service.AccountService;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * service.
 *
 * @author wangwenjie
 * @date 2020-12-10
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void payAccount(Account account) {
        System.out.println("开始支付...");
        accountMapper.pay(account);
    }

    public void confirm(Account account){
        System.out.println("支付成功");
    }

    public void cancel(Account account){
        System.out.println("支付失败");
        accountMapper.cancel(account);

    }
}

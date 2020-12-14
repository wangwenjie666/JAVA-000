package code.service.impl;

import code.client.AccountClient;
import code.entity.Account;
import code.entity.Order;
import code.mapper.OrderMapper;
import code.service.OrderService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * order Service.
 *
 * @author wangwenjie
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final AccountClient accountClient;

    public OrderServiceImpl(OrderMapper orderMapper, AccountClient accountClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
    }

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void pay(Order order) {

        order.setStatus("begin");
        orderMapper.saveOrder(order);
        //支付
        Account account = new Account();
        account.setUserId("001");
        account.setMoney(10);
        accountClient.payAccount(account);

    }

    public void confirm(Order order) {
        order.setStatus("submit");
        orderMapper.updateOrder(order);
    }

    public void cancel(Order order) {
        order.setStatus("rollback");
        orderMapper.updateOrder(order);
    }
}

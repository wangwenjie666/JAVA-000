package code.service;

import code.client.AccountClient;
import code.entity.Account;
import code.entity.Order;
import code.mapper.OrderMapper;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

/**
 * order Service.
 *
 * @author wangwenjie
 */
@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final AccountClient accountClient;

    public OrderService(OrderMapper orderMapper, AccountClient accountClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
    }

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void pay() {
        Order order = new Order();
        order.setStatus("处理中");
        orderMapper.saveOrder(order);
        //支付
        Account account = new Account();
        account.setUserId("001");
        account.setMoney(10);
        accountClient.payAccount(account);

    }

    public void confirm(){
        Order order = new Order();
        order.setStatus("成功");
        orderMapper.saveOrder(order);
    }

    public void cancel(){
        Order order = new Order();
        order.setStatus("失败");
        orderMapper.saveOrder(order);
    }
}

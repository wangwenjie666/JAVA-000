package code.mapper;

import code.entity.Account;
import org.apache.ibatis.annotations.Update;

/**
 * order mapper.
 *
 * @author wangwenjie
 * @date 2020-12-10
 */
public interface AccountMapper {

    @Update("update t_account set money = money - #{money} where user_id = #{userId}")
    void pay(Account account);

    @Update("update t_account set money = money + #{money} where user_id = #{userId}")
    void cancel(Account account);
}

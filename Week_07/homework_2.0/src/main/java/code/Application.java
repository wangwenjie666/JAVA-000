package code;

import code.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * ShardingSphere-jdbc 的 Master-Slave 功能 1）SQL 解析和事务管理，自动实现读写分离 2）解决”写完读”不一致的问题
 *
 * @author wangwenjie
 * @date 2020-12-03
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final UserService userService;

    public Application(UserService userService) {
        this.userService = userService;
    }



    @Bean
    public ApplicationRunner init() {
        return args -> {
//            userService.insertUser("hello world", "system");
//
//            userService.selectUser();
        };
    }
}

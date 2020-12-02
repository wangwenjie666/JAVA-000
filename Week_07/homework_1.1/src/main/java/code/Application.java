package code;

import code.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * jdbcTemplate 多数据源配置 & 读写分离
 * routingDataSource 改造 https://blog.csdn.net/noralthank/article/details/84633102
 *
 * @author wangwenjie
 * @date 2020-12-01
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
            userService.insertUser();

            userService.selectUser(1);
        };
    }
}

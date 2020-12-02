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
            System.out.println("==> insert begin");
            userService.insertUser("hello world", "system");
            System.out.println("==> insert end");

            System.out.println("==> select begin");
            userService.selectUser();
            System.out.println("==> select end");
        };
    }
}

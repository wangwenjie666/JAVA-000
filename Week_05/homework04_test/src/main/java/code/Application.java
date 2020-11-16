package code;

import code.entity.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-16
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired(required = false)
    private Foo foo;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            if (foo == null) {
                throw new RuntimeException("foo配置未自动注入");
            }
            System.out.println("获取配置信息：" + foo.getName());
        };
    }
}

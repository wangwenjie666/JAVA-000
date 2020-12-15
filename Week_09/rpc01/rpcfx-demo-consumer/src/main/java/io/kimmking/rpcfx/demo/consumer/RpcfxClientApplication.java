package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = "io.kimmking")
public class RpcfxClientApplication {

    // 二方库
    // 三方库 lib
    // nexus, userserivce -> userdao -> user
    //

    public static void main(String[] args) {
        SpringApplication.run(RpcfxClientApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            // UserService service = new xxx();
            // service.findById

            UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
            User user = userService.findById(1);
            System.out.println("find user id=1 from server: " + user.getName());

            OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
            Order order = orderService.findOrderById(1992129);
            System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));

            // 新加一个OrderService
        };
    }

}

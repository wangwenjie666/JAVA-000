package io.kimmking.rpcfx.demo.provider;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.UserService;
import io.kimmking.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "io.kimmking.rpcfx")
public class RpcfxServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcfxServerApplication.class, args);
    }

    @Autowired
    RpcfxInvoker invoker;

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }

    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver) {
        return new RpcfxInvoker(resolver);
    }

    @Bean
    public RpcfxResolver createResolver() {
        return new DemoResolver();
    }

    // 能否去掉name
    //
    @Bean
    public UserService createUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public OrderService createOrderService() {
        return new OrderServiceImpl();
    }

    /**
     * 自定义fastjson配置.
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteClassName, SerializerFeature.DisableCircularReferenceDetect);
        config.setCharset(StandardCharsets.UTF_8);
        converter.setFastJsonConfig(config);

        List<MediaType> types = new ArrayList<>();
        types.add(MediaType.APPLICATION_JSON);
        types.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(types);

        return converter;
    }

}

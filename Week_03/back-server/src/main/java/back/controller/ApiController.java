package back.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * api
 *
 * @author wangwenjie
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        final String headerKey = "nio";
        String value = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader(headerKey);

        log.info("==> header key = [{}],value = [{}]", headerKey, value);
        log.info("==> 请求成功.... ");
        return "helloworld";
    }
}

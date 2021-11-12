package com.mi.fallback;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ServerFallBack
 * @Author yangli
 * @Date 2021/4/29 14:59
 * @Description:
 */
@RestController
public class ServerFallBack {

    @RequestMapping("fallback/{name}/get")
    public String serverFallback(@PathVariable("name") String name) {
        return "微服务:" + name + "--访问超时,请稍后再试";
    }
}

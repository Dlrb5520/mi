package com.mi;

import com.mi.annotation.EnableMiLettuceRedis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName AuthApplication
 * @Author yangli
 * @Date 2021/7/9 16:23
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mi.auth.mapper")
@EnableMiLettuceRedis
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}

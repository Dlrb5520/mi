package com.api.goods;

import com.mi.annotation.EnableMiOauth2FeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName GoodsApiApplication
 * @Author yangli
 * @Date 2021/10/22 14:43
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableMiOauth2FeignClient
public class GoodsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApiApplication.class,args);
    }
}

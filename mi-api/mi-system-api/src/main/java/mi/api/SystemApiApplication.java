package mi.api;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.mi.annotation.EnableMiOauth2FeignClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName SystemApiApplication
 * @Author yangli
 * @Date 2021/8/23 20:40
 * @Description: system api 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableMiOauth2FeignClient
// 分布式事物
@EnableDistributedTransaction
//@ComponentScan(basePackages = {"com.api.goods.service"})
public class SystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApiApplication.class,args);
    }
}

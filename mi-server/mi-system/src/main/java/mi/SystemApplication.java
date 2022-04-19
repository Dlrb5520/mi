package mi;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @ClassName SystemApplication
 * @Author yangli
 * @Date 2021/8/23 17:33
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableGlobalMethodSecurity(prePostEnabled = true)
// 分布式事物
@EnableDistributedTransaction
@MapperScan(basePackages = {"mi.system.mapper"})
//@ComponentScan("com.api.goods.service")
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}

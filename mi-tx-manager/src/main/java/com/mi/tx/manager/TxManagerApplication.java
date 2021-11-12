package com.mi.tx.manager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName TxManagerApplication
 * @Author yangli
 * @Date 2021/10/22 16:42
 * @Description:
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class TxManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxManagerApplication.class,args);
    }
}

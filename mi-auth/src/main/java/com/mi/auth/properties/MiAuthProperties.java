package com.mi.auth.properties;

import com.mi.auth.config.MiValidateCodeConfig;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName MiAuthProperties
 * @Author yangli
 * @Date 2021/8/19 14:08
 * @Description:
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:mi-auth.properties"})
@ConfigurationProperties(prefix = "mi.auth")
public class MiAuthProperties {

    private MiClientProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    // 免认证路径
    private String anonUrl;

    //验证码配置类
    private MiValidateCodeConfig code = new MiValidateCodeConfig();
}

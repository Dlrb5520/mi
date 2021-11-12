package com.mi.auth.properties;

import lombok.Data;

/**
 * @ClassName MiClientPropertise
 * @Author yangli
 * @Date 2021/8/19 14:20
 * @Description:
 */
@Data
public class MiClientProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}

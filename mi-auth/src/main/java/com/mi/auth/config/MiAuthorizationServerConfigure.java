package com.mi.auth.config;

import com.mi.auth.properties.MiAuthProperties;
import com.mi.auth.properties.MiClientProperties;
import com.mi.auth.service.MiUserDetailService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * @ClassName MiAuthorizationServerConfigure
 * @Author yangli
 * @Date 2021/7/14 21:00
 * @Description: security
 */
@Configuration
@EnableAuthorizationServer
public class MiAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MiUserDetailService userDetailService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private MiAuthProperties authProperties;


    /**
     * 重写该方法  指定 请求客户端id - mi 密码 ： highConcurrency 可以通过 refresh_token 刷新令牌
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        MiClientProperties[] clientProperties = authProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(clientProperties)) {
            for (MiClientProperties client : clientProperties) {
                if (StringUtils.isBlank(client.getClient())) {
                    throw new Exception("client不能为空");
                }
                if (StringUtils.isBlank(client.getSecret())) {
                    throw new Exception("secret不能为空");
                }

                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }

        }

        /*clients.inMemory()
                    .withClient("mi")
                    .secret(passwordEncoder.encode("highConcurrency"))
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("all")
                    .and()
                    .withClient("baidu")
                    .secret(passwordEncoder.encode("highConcurrency"))
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("all");*/



    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices());
    }

    /**
     * 用来保存token(封装在OAuth2AccessToken中) 这里 使用的 是 redisTokenStore
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 指定了令牌的基本配置
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24);
        tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());
        // tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }
}

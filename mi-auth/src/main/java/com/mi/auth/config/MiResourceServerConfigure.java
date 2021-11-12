package com.mi.auth.config;

import com.mi.auth.properties.MiAuthProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @ClassName MiResourceServerConfigure
 * @Author yangli
 * @Date 2021/7/13 20:07
 * @Description: 资源服务器 安全配置类 相当于拦截器
 */
@Configuration
@EnableResourceServer
public class MiResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private MiAuthProperties miAuthProperties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(miAuthProperties.getAnonUrl(),",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated()
                .and().httpBasic();
    }
}

package com.mi.annotation;


import com.mi.config.MiOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MiOAuth2FeignConfigure.class)
public @interface EnableMiOauth2FeignClient {
}

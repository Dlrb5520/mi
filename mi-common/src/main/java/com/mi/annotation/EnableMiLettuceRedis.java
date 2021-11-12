package com.mi.annotation;

import com.mi.config.MiLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MiLettuceRedisConfigure.class)
public @interface EnableMiLettuceRedis {
}

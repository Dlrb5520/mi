package com.api.goods.service;

import com.api.goods.fallback.GoodsTestFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName IGoodsService
 * @Author yangli
 * @Date 2021/10/22 14:56
 * @Description:
 */
@FeignClient(value = "mi-goods",contextId = "mi-api-goods-test",fallbackFactory = GoodsTestFallback.class)
public interface IGoodsService {

    @PostMapping("goods/test")
    String test (@RequestParam("name") String name);
}

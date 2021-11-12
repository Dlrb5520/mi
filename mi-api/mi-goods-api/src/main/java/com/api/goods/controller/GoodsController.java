package com.api.goods.controller;

import com.api.goods.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName GoodsTest
 * @Author yangli
 * @Date 2021/10/22 15:00
 * @Description:
 */
@RestController("ApiGoodsController")
@RequestMapping("goods")
@Slf4j
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    @PostMapping("test")
    public String test (@RequestParam("name") String name) {
        return goodsService.test(name);
    }
}

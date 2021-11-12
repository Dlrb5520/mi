package mi.goods.controller;

import com.mi.entity.Orders;
import com.mi.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import mi.api.system.service.IUserService;
import mi.goods.service.ITestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName TestController
 * @Author yangli
 * @Date 2021/10/22 14:50
 * @Description:
 */
@RestController()
@RequestMapping("goods")
@Slf4j
public class TestController {

    @Resource
    private IUserService userService;

    @Resource
    private ITestService iTestService;



    @PostMapping("test")
    public String test (String name) {
        log.info("feign调用==========,{}",name);
        iTestService.test();
        return userService.test(name);
    }
}

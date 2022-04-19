package mi.goods.controller;

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
    private ITestService iTestService;



    @PostMapping("test")
    public String test (String name) {
        iTestService.test();
        return "";
    }
}

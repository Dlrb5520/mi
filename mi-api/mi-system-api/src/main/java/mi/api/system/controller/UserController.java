package mi.api.system.controller;

import lombok.extern.slf4j.Slf4j;
import mi.api.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName UserController
 * @Author yangli
 * @Date 2021/8/24 11:52
 * @Description:
 */
@RestController("ApiUserController")
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("test")
    public String test(@RequestParam Map<String,String> params) {
        log.info("调用！！！！！！！！！！！");
        return this.iUserService.test(params.get("name"));
    }

    @GetMapping("test1")
    public String test1() {
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello world";
    }
}

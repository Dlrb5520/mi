package mi.system.controller;

import lombok.extern.slf4j.Slf4j;
import mi.system.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Author yangli
 * @Date 2021/8/23 17:48
 * @Description:
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("test")
    public String test(@RequestParam("name") String name)  {
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("铠甲合体！！！！！！！！！帝皇铠甲");
        //
        // this.userService.test(name);
        return "test-system-" + name;
    }



}

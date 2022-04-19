package mi.api.system.controller;

//import com.api.goods.service.IGoodsService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import mi.api.system.service.IGoodsService;
import mi.api.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private IGoodsService iGoodsService;

    @GetMapping("test")
    @LcnTransaction
    public void test(@RequestParam Map<String,String> params) {
        log.info("调用！！！！！！！！！！！");
        String resultStr = null;
        try {
            resultStr = this.iUserService.test(params.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("异常了...................");
        }

        iGoodsService.test("wowowo");
        log.info("库存更新完毕----------------");

//        throw new RuntimeException("抛个异常~");
//        return resultStr;
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

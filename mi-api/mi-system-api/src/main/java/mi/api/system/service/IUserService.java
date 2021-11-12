package mi.api.system.service;

import mi.api.system.fallback.SystemUserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName UserService
 * @Author yangli
 * @Date 2021/8/24 11:58
 * @Description:
 */
@FeignClient(value = "mi-system",contextId = "mi-api-user",fallbackFactory = SystemUserFallback.class)
public interface IUserService {

    @GetMapping("user/test")
    String test (@RequestParam("name") String name);
}

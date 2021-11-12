package mi.api.system.fallback;

import feign.hystrix.FallbackFactory;
import mi.api.system.service.IUserService;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserFallback
 * @Author yangli
 * @Date 2021/8/24 12:00
 * @Description:
 */
@Component
public class SystemUserFallback implements FallbackFactory<IUserService> {

    @Override
    public IUserService create(Throwable throwable) {
        return new IUserService() {
            @Override
            public String test(String name) {
                return "system-超时了----------------------";
            }
        };
        // return null;
    }
}

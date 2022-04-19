package mi.api.system.fallback;

import feign.hystrix.FallbackFactory;
import mi.api.system.service.IGoodsService;
import org.springframework.stereotype.Component;

/**
 * @ClassName GoodsTestFallback
 * @Author yangli
 * @Date 2021/10/22 14:58
 * @Description:
 */
@Component
public class GoodsTestFallback implements FallbackFactory<IGoodsService> {
    @Override
    public IGoodsService create(Throwable throwable) {
        return new IGoodsService() {
            @Override
            public String test(String name) {
                return "goods-超时了----------------------";
            }
        };
    }
}

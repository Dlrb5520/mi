package mi.system.service.impl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import mi.system.entity.Order;
import mi.system.mapper.OrderMapper;
import mi.system.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Author yangli
 * @Date 2021/10/21 11:23
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl  implements IUserService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    @LcnTransaction
    public String test(String str) {
        log.info("炎龙铠甲！！！！！！！！,{}",str);

        Order orders = new Order();
        orders.setOrderNum(UUID.randomUUID().toString());
        orders.setGoodsId(1);
        orders.setGoodsName("iphone13");
        orderMapper.addOrder(orders);
        log.info("生成订单====================");
        return str;
    }
}

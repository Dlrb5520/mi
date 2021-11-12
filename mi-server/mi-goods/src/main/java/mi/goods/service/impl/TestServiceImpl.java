package mi.goods.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.mi.entity.Orders;
import com.mi.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import mi.goods.service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName TestServiceImpl
 * @Author yangli
 * @Date 2021/10/25 14:56
 * @Description:
 */
@Service
@Slf4j
public class TestServiceImpl implements ITestService {

    @Resource
    private IOrdersService iOrdersService;

    @Override
    @LcnTransaction
    public void test() {
        Orders orders = new Orders();
        orders.setOrderNum(UUID.randomUUID().toString());
        orders.setGoodsId(1);
        orders.setGoodsName("iphone13");
        iOrdersService.save(orders);
        log.info("生成订单====================");
    }
}

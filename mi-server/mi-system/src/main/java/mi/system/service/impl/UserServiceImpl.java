package mi.system.service.impl;

import com.mi.entity.Goods;
import com.mi.entity.Orders;
import com.mi.service.IGoodsService;
import com.mi.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
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
    private IGoodsService iGoodsService;

    @Override
    public String test(String str) {
        log.info("炎龙铠甲！！！！！！！！,{}",str);
        Goods goods = this.iGoodsService.getById(1);
        Integer count = (Integer.parseInt(goods.getStock()) - 1);
        goods.setStock(String.valueOf(count));
        this.iGoodsService.saveOrUpdate(goods);
        log.info("更新库存=================");
        return str;
    }
}

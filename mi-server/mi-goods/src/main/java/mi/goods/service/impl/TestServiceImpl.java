package mi.goods.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import mi.goods.entity.Goods;
import mi.goods.mapper.GoodsMapper;
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
    private GoodsMapper goodsMapper;

    @Override
    @LcnTransaction
    public void test() {
        Goods goods = new Goods();
        goods.setId(1);
        goodsMapper.updateGoods(goods);
        log.info("更新库存=================");
    }
}

package mi.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mi.goods.entity.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName GoodsMapper
 * @Author yangli
 * @Date 2021/11/15 15:15
 * @Description:
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 更新
     * @param goods
     * @return
     */
    int updateGoods(@Param("goods") Goods goods);
}

package mi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mi.system.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName OrderMapper
 * @Author yangli
 * @Date 2021/11/15 14:30
 * @Description:
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 保存订单
     * @param order
     * @return
     */
    int addOrder(@Param("order")Order order);
}

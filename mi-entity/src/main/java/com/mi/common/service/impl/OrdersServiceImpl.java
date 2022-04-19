package com.mi.common.service.impl;

import com.mi.common.entity.Orders;
import com.mi.common.mapper.OrdersMapper;
import com.mi.common.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangli
 * @since 2021-11-15
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}

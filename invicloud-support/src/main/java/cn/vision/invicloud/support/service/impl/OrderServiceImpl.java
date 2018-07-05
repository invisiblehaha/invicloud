package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.mapper.OrderMapper;
import cn.vision.invicloud.support.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}

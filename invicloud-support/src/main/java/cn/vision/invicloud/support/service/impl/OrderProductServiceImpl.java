package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.OrderProduct;
import cn.vision.invicloud.support.mapper.OrderProductMapper;
import cn.vision.invicloud.support.service.IOrderProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements IOrderProductService {

}

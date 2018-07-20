package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.OrderProduct;
import cn.vision.invicloud.support.mapper.OrderProductMapper;
import cn.vision.invicloud.support.service.IOrderProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public List<OrderProduct> listByOrderId(Long orderId) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(orderId);
        return orderProductMapper.selectList(new EntityWrapper<OrderProduct>(orderProduct));
    }

    @Override
    public Integer addOrderItemToDB(String orderId,String productId,String productNumber,String productPrice){
        orderProductMapper.addOrderItemToDB(orderId,productId,productNumber,productPrice);
        return 1;
    }
}

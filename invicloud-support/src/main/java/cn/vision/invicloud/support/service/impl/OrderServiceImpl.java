package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.mapper.OrderMapper;
import cn.vision.invicloud.support.mapper.OrderProductMapper;
import cn.vision.invicloud.support.pojo.vo.OrderVO;
import cn.vision.invicloud.support.service.IOrderService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public BasePageDTO<OrderVO> list(Integer userId, PageInfo pageInfo,String search) {
        pageInfo.setTotal(orderMapper.getCount(userId));
        List<OrderVO> orderVOs = orderMapper.list(userId, pageInfo, search);
        return new BasePageDTO<OrderVO>(pageInfo, orderVOs);

    }
	 @Override
    public Long addOrderToDB(String customerId, String payType, String buyAmount, String payAmount){
        Order order=new Order();
        order.setCustomerId(Integer.parseInt(customerId));
        order.setPayType(1);
        order.setBuyAmount(Integer.parseInt(buyAmount));
        order.setPayAmount(new BigDecimal(payAmount));

        int oderId=orderMapper.addOrderToDB(order);
        System.out.println(oderId);
        long id=order.getOrderId();
        System.out.println(id);
        return id;
    }
}

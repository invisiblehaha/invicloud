package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.mapper.OrderMapper;
import cn.vision.invicloud.support.mapper.OrderProductMapper;
import cn.vision.invicloud.support.pojo.vo.OrderVO;
import cn.vision.invicloud.support.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BasePageDTO<OrderVO> list(Integer userId, PageInfo pageInfo) {
        pageInfo.setTotal(orderMapper.getCount(userId));
        List<OrderVO> orderVOs = orderMapper.list(userId, pageInfo);
        return new BasePageDTO<OrderVO>(pageInfo, orderVOs);

    }
}

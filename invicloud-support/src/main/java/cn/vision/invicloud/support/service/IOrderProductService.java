package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.OrderProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IOrderProductService extends IService<OrderProduct> {

    /**
     * 根据订单ID查找订单详情
     * @param orderId 订单ID
     * @return
     */
    List<OrderProduct> listByOrderId(Long orderId);

    Integer addOrderItemToDB(String orderId,String productId,String productNumber,String productPrice);
}

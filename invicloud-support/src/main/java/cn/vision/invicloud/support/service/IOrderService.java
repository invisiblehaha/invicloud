package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.entity.OrderProduct;
import cn.vision.invicloud.support.entity.Product;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IOrderService extends IService<Order> {

    /**
     * 根据订单信息创建订单
     * @param order 订单信息
     * @param Products 商品列表
     * @param userId 用户ID
     * @return
     */
    Long insertOrder(Order order, List<Product> Products, Integer userId);

    /**
     * 根据用户ID、分页信息、搜索内容查找订单列表
     * @param page 分页信息
     * @param userId 用户ID
     * @param search 搜索内容
     * @return
     */
    Page<Order> list(Page<Order> page,Long userId, String search);

    /**
     * 根据用户ID、订单编号查找订单信息
     * @param userId 用户ID
     * @param orderNumber 订单编号
     * @return
     */
    Order getOrder(Long userId, Long orderNumber);

    /**
     * 根据订单编号,用户ID,订单状态查找订单信息
     * @param orderNumber 订单编号
     * @param userId 用户ID
     * @return
     */
    Order getByOrderNumber(Long orderNumber, Long userId, Integer status);

    /**
     * 根据订单编号,用户ID取消订单
     * @param orderNumber 订单编号
     * @param userId 用户ID
     * @return
     */
    Integer updateCancelOrder(Long orderNumber, Long userId);

    /**
     * 根据订单编号,用户ID,送货时间类型修改送货时间
     * @param orderNumber  订单编号
     * @param shipmentTime 送货时间
     * @param userId 用户ID
     * @return
     */
    Integer updateShipmentTime(Long orderNumber, Integer shipmentTime, Long userId);
}

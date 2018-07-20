package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.pojo.vo.OrderVO;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;

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
     * 根据用户ID、分页信息查找订单列表
     * @param userId 用户ID
     * @param pageInfo 分页信息
     * @return
     */
    BasePageDTO<OrderVO> list(Integer userId, PageInfo pageInfo, String search);

    Long addOrderToDB(String customerId, String payType, String buyAmount, String payAmount);
}

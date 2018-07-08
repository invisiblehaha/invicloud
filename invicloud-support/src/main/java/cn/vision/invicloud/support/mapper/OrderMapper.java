package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Order;
import cn.vision.invicloud.support.pojo.vo.OrderVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据用户ID、搜索内容查找订单总记录数
     * @param userId 用户ID
     * @return
     */
    Integer getCount(@Param("userId") Integer userId);

    /**
     * 根据用户ID、分页信息、搜索内容查找订单列表
     * @param userId 用户ID
     * @param pageInfo 分页信息
     * @return
     */
    List<OrderVO> list(@Param("userId") Integer userId, @Param("pageInfo") PageInfo pageInfo);

}

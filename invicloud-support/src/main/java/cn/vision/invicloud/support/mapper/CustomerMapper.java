package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.pojo.vo.CustomerVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 根据人脸标识查找用户信息
     * @param customerToken 人脸标识
     * @return
     */
    CustomerVO getByCustomerToken(@Param("customerToken")String  customerToken);

    /**
     * 根据用户ID查找用户显示信息
     * @param customerId 用户ID
     * @return
     */
    CustomerVO getById(@Param("customerId")Integer customerId);
    /**
     * 根据用户ID查找用户显示信息
     * @param customerId 用户ID
     * @return
     */
    Customer getBycustomerId(@Param("customerId")Integer customerId);
    /**
     * 根据顾客等级分页显示信息
     * @param pageInfo 分页信息
     * @param noble 顾客等级
     * @param rowBounds 分页实体
     * @return
     */
    List<CustomerVO> listByPage(@Param("pageInfo") PageInfo pageInfo, @Param("noble") Integer noble, RowBounds rowBounds);

    List<CustomerVO> listByPage2(@Param("pageInfo") PageInfo pageInfo, @Param("search") String search, RowBounds rowBounds);

    //返回即将被注册的userid
    Integer getLastestPlusCustomerId();
}

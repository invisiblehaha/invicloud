package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.pojo.vo.CustomerVO;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 顾客表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface ICustomerService extends IService<Customer> {

    /**
     * 创建顾客
     * @param customer 顾客信息
     * @return
     */
    Integer insertCustomer(Customer customer);

    /**
     * 根据人脸标识查找顾客信息
     * @param token 人脸标识
     * @return
     */
    CustomerVO getByCustomerToken(@Param("customerToken") String token);

    /**
     * 根据顾客ID获取顾客显示信息
     * @param customerId 顾客ID
     * @return
     */
    CustomerVO getById(@Param("customerId") Integer customerId);
    Customer getBycustomerId(@Param("customerId") Integer customerId);
    /**
     * 根据顾客等级(可)分页显示顾客
     * @param pageInfo
     * @param noble
     * @return
     */

    BasePageDTO<CustomerVO> listByPage(@Param("pageInfo") PageInfo pageInfo, @Param("noble") Integer noble);

    BasePageDTO<CustomerVO> listByPage2(@Param("pageInfo") PageInfo pageInfo, @Param("search") String search);

    //返回返回即将被注册的userid
    Integer getLastestPlusCustomerId();
}

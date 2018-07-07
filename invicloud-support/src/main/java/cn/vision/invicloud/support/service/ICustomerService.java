package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Customer;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface ICustomerService extends IService<Customer> {

    /**
     * 创建用户
     * @param user 用户信息
     * @return
     */
    Integer insertUser(Customer user);

    /**
     * 通过面部表示查找顾客
     * @param token 面部标识
     * @return Customer
     */
    Customer getByToken(String token);

    /**
     * 根据邮箱获取用户信息
     * @param email 电子邮箱
     * @return
     */
    Customer getByEmail(String email);

    /**
     * 根据用户ID获取用户显示信息
     * @param userId 用户ID
     * @return
     */
    Customer getById(Long userId);
}

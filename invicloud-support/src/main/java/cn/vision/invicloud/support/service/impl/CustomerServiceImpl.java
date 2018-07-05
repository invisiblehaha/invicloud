package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.mapper.CustomerMapper;
import cn.vision.invicloud.support.service.ICustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}

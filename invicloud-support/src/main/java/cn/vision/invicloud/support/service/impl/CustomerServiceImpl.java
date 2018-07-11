package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.mapper.CustomerMapper;
import cn.vision.invicloud.support.pojo.vo.CustomerVO;
import cn.vision.invicloud.support.service.ICustomerService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Integer insertCustomer(Customer customer) {
        return customerMapper.insert(customer);
    }

    @Override
    public CustomerVO getByCustomerToken(String token) {
        return customerMapper.getByCustomerToken(token);
    }

    @Override
    public CustomerVO getById(Integer customerId) {
        return customerMapper.getById(customerId);
    }

    @Override
    public BasePageDTO<CustomerVO> listByPage(PageInfo pageInfo, Integer noble) {
        Page<CustomerVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<CustomerVO> adverts = customerMapper.listByPage(pageInfo, noble, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<CustomerVO>(pageInfo, adverts);
    }

    @Override
    public BasePageDTO<CustomerVO> listByPage2(PageInfo pageInfo, String search) {
        Page<CustomerVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<CustomerVO> adverts = customerMapper.listByPage2(pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<CustomerVO>(pageInfo, adverts);
    }
}

package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.entity.Specification;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 规格表
 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface ISpecificationService extends IService<Specification> {

    /**
     * 根据商品编号和状态查找商品
     * @param productNumber 商品编号
     * @param status 状态
     * @return
     */
    Product getByNumber(Integer productNumber);

}

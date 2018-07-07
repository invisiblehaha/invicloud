package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Product;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IProductService extends IService<Product> {

    /**
     * 根据商品序号查找商品
     * @param productId 商品序号
     * @return
     */
    Product getById(Long productId);
}

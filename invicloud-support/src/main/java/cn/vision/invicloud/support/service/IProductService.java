package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.pojo.dto.ProductPageDTO;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
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
     * 根据商品编号和状态查找商品
     * @param productId 商品Id
     * @return
     */
    ProductVO getById(Integer productId);

    ProductPageDTO listByPage(PageInfo pageInfo, String search);
}

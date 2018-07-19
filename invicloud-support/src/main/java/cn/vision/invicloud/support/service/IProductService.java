package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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
    /**
     * 根据分页信息/搜索内容查找商品
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @return
     */
    BasePageDTO<Product> list(PageInfo pageInfo, String search);

    List<ProductVO> getAllProduct();

    List<ProductVO> getProductBySearch(String search);
}

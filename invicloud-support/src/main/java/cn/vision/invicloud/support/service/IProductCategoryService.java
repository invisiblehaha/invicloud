package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.entity.ProductCategory;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 商品分类关联表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    /**
     * 根据类目ID、排序、分页查找显示商品列表
     * @param categoryIds 类目ID
     * @param pageInfo 分页实体
     * @return
     */
    BasePageDTO<ProductVO> listProducts(List<Integer> categoryIds, PageInfo pageInfo);

    /**
     * 查找商品的所有父类目
     * @param productId
     * @return
     */
    List<Category> listUpperByProductId(Integer productId);

    /**
     * 根据categoryId列出所有商品
     * @param id
     * @return
     */
    List<ProductVO> getCateProduct(String id);
}

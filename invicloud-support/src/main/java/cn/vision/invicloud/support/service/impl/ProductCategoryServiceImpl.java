package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.ProductCategory;
import cn.vision.invicloud.support.mapper.ProductCategoryMapper;
import cn.vision.invicloud.support.service.IProductCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类关联表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

}

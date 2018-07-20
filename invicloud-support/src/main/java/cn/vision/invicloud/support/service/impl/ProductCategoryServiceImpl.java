package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.entity.ProductCategory;
import cn.vision.invicloud.support.mapper.CategoryMapper;
import cn.vision.invicloud.support.mapper.ProductCategoryMapper;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import cn.vision.invicloud.support.service.IProductCategoryService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public BasePageDTO<ProductVO> listProducts(List<Integer> categoryIds, PageInfo pageInfo) {
        Page<ProductVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<ProductVO> adverts = productCategoryMapper.listByPage(categoryIds,pageInfo, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<ProductVO>(pageInfo, adverts);
    }

    @Override
    public List<Category> listUpperByProductId(Integer productId) {

        ProductCategory queryProductCategory = new ProductCategory();
        queryProductCategory.setProductId(productId);
        ProductCategory productCategory = productCategoryMapper.selectOne(queryProductCategory);

        // 查找类目ID的所有父类目
        if (productCategory != null) {
            List<Category> categories = new ArrayList<>();

            // 将该商品的所属类添加到列表中
            Category category = categoryMapper.selectById(productCategory.getCategoryId());
            categories.add(category);

            // 查找类目ID的所有父类目
            getUpperCategory(categories, productCategory.getCategoryId());

            // 对类目列表进行反转
            Collections.reverse(categories);
            return categories;
        }
        return null;
    }

    /**
     * 查找类目ID的所有父类目
     * @param categories 父类目列表
     * @param categoryId 类目ID
     */
    private void getUpperCategory(List<Category> categories, Integer categoryId) {
        Category upperCategory = categoryMapper.getUpper(categoryId);
        if (upperCategory != null) {
                categories.add(upperCategory);
            getUpperCategory(categories, upperCategory.getCategoryId());
        }
    }

    public List<ProductVO> getCateProduct(String id){
        List<ProductVO> cateProduct = productCategoryMapper.getCateProduct(id);
        return cateProduct;
    }
}

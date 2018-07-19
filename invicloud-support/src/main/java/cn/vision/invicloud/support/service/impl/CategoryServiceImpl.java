package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.mapper.CategoryMapper;
import cn.vision.invicloud.support.mapper.ProductCategoryMapper;
import cn.vision.invicloud.support.service.ICategoryService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public Category getById(Integer categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return categoryMapper.selectOne(category);
    }

    @Override
    public List<Category> listLowerCategories(Integer categoryId) {
        // 查找子级分类
        List<Category> lowerCategories = categoryMapper.listLower(categoryId);

        /*if (lowerCategories.isEmpty()) {
            Category upperCategory = categoryMapper.getUpper(categoryId);
            lowerCategories = categoryMapper.listLower(upperCategory.getCategoryId());
        }*/
        return lowerCategories;
    }

    @Override
    public BasePageDTO<Category> listParentByPage(PageInfo pageInfo, String search, Integer parentId) {
        Page<Category> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<Category> categories = categoryMapper.listParentByPage(parentId, pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<Category>(pageInfo, categories);
    }
}

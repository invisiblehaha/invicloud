package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Category;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sun.org.apache.xml.internal.resolver.Catalog;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 根据类目ID查找当前类目信息
     * @param categoryId 类目ID
     * @return
     */
    Category getById(Long categoryId);

    /**
     * 根据类目ID查找子类目列表（如果沒有则返回当前目录列表）
     * @param categoryId 类目ID
     * @param status 子类目状态
     * @return
     */
    List<Category> listLowerCategories(Long categoryId, Integer status);

    /**
     * 根据类目ID查找父类目列表（如果沒有则返回当前目录列表）
     * @param categoryId 类目ID
     * @param status 父类目状态
     * @return
     */
    List<Category> listUpperCategories(Long categoryId, Integer status);

    /**
     * 根据商品ID查找父类目列表
     * @param productId 商品ID
     * @param status 父类目状态
     * @return
     */
    List<Category> listUpperByProductId(Long productId, Integer status);

    /**
     * 根据分页信息/搜索内容查找一级分类列表
     * @param page 分页信息
     * @param search 搜索内容
     * @param parentId 父类目ID
     * @return
     */
    Page<Category> listParentByPage(Page<Catalog> page, String search, Long parentId);

    /**
     * 更新类目状态
     * @param categoryId 类目ID
     * @return
     */
    Integer updateStatus(Long categoryId);

    /**
     * 更新类目
     * @param category 类目信息
     * @return
     */
    Integer updateCategory(Category category);

}

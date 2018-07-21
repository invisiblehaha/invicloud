package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Category;
import com.baomidou.mybatisplus.service.IService;

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
    Category getById(Integer categoryId);

    /**
     * 根据类目ID查找子类目列表（如果沒有则返回当前目录列表）
     * @param categoryId 类目ID
     * @return
     */
    List<Category> listLowerCategories(Integer categoryId);

    /**
     * 根据分页信息/搜索内容查找一级分类列表
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @param parentId 父类目ID
     * @return
     */
    BasePageDTO<Category> listParentByPage(PageInfo pageInfo, String search, Integer parentId);

    List<Category> listLower(Integer categoryId);
}

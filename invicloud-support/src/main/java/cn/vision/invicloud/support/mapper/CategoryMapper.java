package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Category;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据类目ID查找子类目ID
     * @param categoryId 父类目ID
     * @return
     */
    List<Integer> listIds(@Param("categoryId") Integer categoryId);

    /**
     * 根据父类目ID查找子类目列表
     * @param categoryId 父类目ID
     * @return
     */
    List<Category> listLower(@Param("categoryId") Integer categoryId);

    /**
     * 根据子类目ID查找父目录
     * @param categoryId 子类目ID
     * @return
     */
    Category getUpper(@Param("categoryId") Integer categoryId);

    /**
     * 根据分页信息/搜索内容/父类目ID查找分类列表
     * @param parentId 父类目ID
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @return
     */
    List<Category> listParentByPage(@Param("parentId") Integer parentId, @Param("pageInfo") PageInfo pageInfo,
                                    @Param("search") String search, RowBounds rowBounds);
}

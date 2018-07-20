package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.ProductCategory;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 商品分类关联表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    /**
     * 根据类目ID列表、分类、排序查找商品列表及标签（分类查找）
     * @param categoryIds 目录ID列表
     * @param pageInfo 排序方式
     * @param rowBounds 分页实体类
     * @return
     */
    List<ProductVO> listByPage(@Param("categoryIds") List<Integer> categoryIds,
                               @Param("pageInfo") PageInfo pageInfo, RowBounds rowBounds);

    List<ProductVO> getCateProduct(@Param("id") String id);
}

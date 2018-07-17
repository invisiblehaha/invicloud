package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 根据商品Id查找商品
     * @param productId 商品Id
     * @return
     */
    ProductVO getById(@Param("productNumber") Integer productId);

    /**
     * 根据分页信息/搜索内容查找产品列表
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @param rowBounds 分页实体
     * @return
     */
    List<ProductVO> listByPage(@Param("pageInfo") PageInfo pageInfo, @Param("search") String search, RowBounds rowBounds);
}

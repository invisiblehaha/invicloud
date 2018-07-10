package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
}

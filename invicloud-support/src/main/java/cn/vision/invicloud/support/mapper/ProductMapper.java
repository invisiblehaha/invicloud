package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import cn.vision.invicloud.support.pojo.vo.SingProductAnalyVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
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
    ProductVO getById(@Param("productId") Integer productId);

    List<Product> list(@Param("pageInfo") PageInfo pageInfo,
                       @Param("search") String search, RowBounds rowBounds);
    List<ProductVO> getAllProduct();

    List<SingProductAnalyVO> analySing(@Param("productId")Integer productId);
	//自己写
    Integer deleteByProductId(@Param("productId") Integer productId);


    Integer insertInto( @Param("productName") String productName,
                       @Param("productPrice") BigDecimal productPrice,@Param("stock") Integer stock );
					   
    List<ProductVO> getProductBySearch(@Param("search")String search);					   

}

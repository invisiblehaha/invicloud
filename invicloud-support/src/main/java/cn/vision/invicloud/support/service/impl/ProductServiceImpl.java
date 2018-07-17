package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.mapper.ProductMapper;
import cn.vision.invicloud.support.pojo.dto.ProductPageDTO;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import cn.vision.invicloud.support.service.IProductService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductVO getById(Integer productId) {
        return productMapper.getById(productId);
    }

    @Override
    public ProductPageDTO listByPage(PageInfo pageInfo, String search) {
        Page<ProductVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<ProductVO> productVOs = productMapper.listByPage(pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new ProductPageDTO(productVOs, pageInfo);

    }

}

package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.mapper.ProductMapper;
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
    public BasePageDTO<Product> list(PageInfo pageInfo, String search) {
        Page<Product> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<Product> products = productMapper.list( pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<Product>(pageInfo, products);
    }

    @Override
    public List<ProductVO> getAllProduct(){
        List<ProductVO> allProduct=productMapper.getAllProduct();
        return allProduct;
    }

    @Override
    public List<ProductVO> getProductBySearch(String search){
        List<ProductVO> allProduct=productMapper.getProductBySearch(search);
        return allProduct;
    }
}

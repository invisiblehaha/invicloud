package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.mapper.ProductMapper;
import cn.vision.invicloud.support.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

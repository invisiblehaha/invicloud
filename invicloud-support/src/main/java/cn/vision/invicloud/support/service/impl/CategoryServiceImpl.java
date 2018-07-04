package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.mapper.CategoryMapper;
import cn.vision.invicloud.support.service.ICategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}

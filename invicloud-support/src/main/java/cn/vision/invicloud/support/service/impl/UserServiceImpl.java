package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.mapper.UserMapper;
import cn.vision.invicloud.support.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

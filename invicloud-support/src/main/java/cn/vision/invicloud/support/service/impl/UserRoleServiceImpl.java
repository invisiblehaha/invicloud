package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.UserRole;
import cn.vision.invicloud.support.mapper.UserRoleMapper;
import cn.vision.invicloud.support.service.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员角色关联表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}

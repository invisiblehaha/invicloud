package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.common.PasswordUtils;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.entity.UserRole;
import cn.vision.invicloud.support.mapper.UserMapper;
import cn.vision.invicloud.support.mapper.UserRoleMapper;
import cn.vision.invicloud.support.pojo.dto.UserPageDTO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Integer insertUser(User user, String[] roleIds){
        // 验证用户名
        if (this.checkLoginName(user.getLoginName())) {
            return -1;
        }

        // 插入该用户
        user.setSalt(PasswordUtils.getSalt());
        user.setLoginPassword(PasswordUtils.getMd5(user.getLoginPassword(),user.getSalt()));
        Integer count = userMapper.insert(user);

        // 插入角色列表
        if (roleIds != null && roleIds.length > 0) {
            List<UserRole> userRoles = new ArrayList<>();
            for (int i = 0; i < roleIds.length; i++) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setCreateTime(new Date());
                userRole.setRoleId(Integer.valueOf(roleIds[i]));
                userRoles.add(userRole);
            }
            userRoleMapper.insertUserRoles(userRoles);
        }
        return count;
    }

    @Override
    public User getByLoginName(String loginName) {
        User user = new User();
        user.setLoginName(loginName);
        return userMapper.selectOne(user);
    }

    @Override
    public UserVO getById(Integer userId) {
        return userMapper.getById(userId);
    }

    @Override
    public UserPageDTO listByPage(PageInfo pageInfo, String search) {
        Page<UserVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<UserVO> userVOs = userMapper.listByPage(pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new UserPageDTO(userVOs, pageInfo);
    }

    @Override
    public Integer updateByUserId(User user) {
        user.setUpdateTime(new Date());
        return userMapper.updateById(user);
    }

    @Override
    public Integer updateUser(User user, String[] roleIds) {
        // 更新用户信息
        Integer count = userMapper.updateById(user);

        // 删除UserRole 表用户记录
        UserRole userRoleById = new UserRole();
        userRoleById.setUserId(user.getUserId());
        userRoleMapper.delete(new EntityWrapper<UserRole>(userRoleById));

        // 插入SystemUserLoginLog 表用户记录
        if (roleIds != null && roleIds.length > 0) {
            List<UserRole> userRoles = new ArrayList<>();
            for (int i = 0; i < roleIds.length; i++) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setCreateTime(new Date());
                userRole.setRoleId(Integer.valueOf(roleIds[i]));
                userRoles.add(userRole);
            }
            // 插入角色列表
            userRoleMapper.insertUserRoles(userRoles);
        }
        return count;
    }

    @Override
    public Integer updatePsw(String nowPassword, String newPassword, Integer userId, String userName) {
        User user = userMapper.selectById(userId);

        String password = PasswordUtils.getMd5(nowPassword, user.getSalt());
        if (user.getLoginPassword() == null || !user.getLoginPassword().equals(password)) {
            return -1;
        }

        User updateUser = new User();
        updateUser.setUserId(userId);
        updateUser.setLoginPassword(PasswordUtils.getMd5(newPassword, user.getSalt()));
        return userMapper.updateById(updateUser);
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        // 删除用户角色记录
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRoleMapper.delete(new EntityWrapper<UserRole>(userRole));
        // 删除用户信息
        return userMapper.deleteById(userId);
    }

    private boolean checkLoginName(String loginName) {
        User user = new User();
        user.setLoginName(loginName);
        int count = userMapper.selectCount(new EntityWrapper<User>(user));
        if (count > 0) {
            return true;
        }
        return false;
    }

}

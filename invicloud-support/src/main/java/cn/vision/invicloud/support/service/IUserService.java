package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.User;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IUserService extends IService<User> {

    /**
     * 创建用户并授予权限
     * @param user 管理员信息
     * @param roleIds 角色记录ID
     * @return
     */
    Integer insertUser(User user, Integer[] roleIds);

    /**
     * 根据用户账号查找用户
     * @param loginName 用户
     * @return
     */
    User getByLoginName(String loginName);

    /**
     * 根据用户ID查找用户
     * @param userId 用户ID
     * @return
     */
    User getById(Integer userId);

    /**
     * 根据分页信息/搜索内容查找用户列表
     * @param page 分页信息
     * @return
     */
    Page<User> selectUserPage(Page<User> page);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return
     */
    Integer updateByUserId(User user);

    /**
     * 更新用户密码
     * @param nowPassword 密码
     * @param newPassword 新密码
     * @param loginName 用户账号
     * @return
     */
    Integer updatePassword(String loginName,String nowPassword, String newPassword);

    /**
     * 根据用户ID删除用户
     * @param userId 用户ID
     * @return
     */
    Integer deleteByUserId(Integer userId);
}

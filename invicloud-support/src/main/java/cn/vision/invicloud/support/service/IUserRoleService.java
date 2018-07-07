package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.UserRole;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 管理员角色关联表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 根据用户ID查找角色
     * @param userId 用户ID
     * @param status 角色状态
     * @return
     */
    List<Role> listByUserId(Integer userId, Integer status);

    /**
     * 根据角色ID查找用户
     * @param page 分页信息
     * @param roleId 角色ID
     * @return
     */
    Page<Role> listByRoleId(Page<Role> page,Integer roleId);

    /**
     * 更新用户角色
     * @param userId 用户ID
     * @param roleIds 角色ID
     * @return
     */
    Integer updateUserRole(Integer userId, Integer[] roleIds);
}

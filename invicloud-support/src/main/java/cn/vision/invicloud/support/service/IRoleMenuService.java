package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Menu;
import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.RoleMenu;
import cn.vision.invicloud.support.pojo.vo.MenuVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    /**
     * 更新角色信息以及角色权限
     * @param role 角色信息
     * @param menuIds 用户授权目录ID
     * @return
     */
    Integer updateRole(Role role, Integer[] menuIds);

    /**
     * 根据角色ID查找权限列表
     * @param roleId 角色ID
     * @return List<RoleMenu>
     */
    List<MenuVO> listByRoleId(Integer roleId);
}

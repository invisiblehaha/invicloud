package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.RoleMenu;
import cn.vision.invicloud.support.pojo.dto.RoleMenuDTO;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
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
     * 根据角色ID列表查找权限列表
     * @param roleIds 角色ID列表
     * @return Set<String>
     */
    Set<String> getByRolesId(Set<String> roleIds);

    /**
     * 根据用户ID查找系统目录
     * @param userId 用户ID
     * @return List<RoleMenuVO>
     */
    List<RoleMenuVO> listByUserId(Integer userId);

    /**
     * 根据目录状态查找系统目录
     * @return List<RoleMenuDTO>
     */
    List<RoleMenuDTO> listRoleMenus();

    /**
     * 根据角色ID查找目录及其是否选中
     * @param roleId 角色ID
     * @return List<RoleMenuVO>
     */
    List<RoleMenuVO> listCheckedMenus(Integer roleId);
}

package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.entity.RoleMenu;
import cn.vision.invicloud.support.pojo.dto.RoleMenuDTO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 插入角色目录表记录
     * @param roleMenus 角色目录表记录列表
     */
    Integer insertRoleMenus(@Param("roleMenus") List<RoleMenu> roleMenus);

    /**
     * 根据角色ID列表查找权限列表
     * @param roleIds 角色ID列表
     * @return
     */
    Set<RoleMenuDTO> listByRoleIds(@Param("roleIds") Set<String> roleIds);

    /**
     * 根据角色ID/目录类型查找权限列表
     * @param roleIds  角色ID列表
     * @param menuType 目录类型
     * @return
     */
    List<RoleMenuDTO> listByRoleIdsAndType(@Param("roleIds") Set<Integer> roleIds,@Param("menuType") Integer menuType);

    /**
     * 根据目录状态查找目录列表
     * @return List<RoleMenuDTO>
     */
    List<RoleMenuDTO> listRoleMenus();
}

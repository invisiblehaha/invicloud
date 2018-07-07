package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.Role;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IRoleService extends IService<Role> {

    /**
     * 创建角色信息及角色权限
     * @param role 角色信息
     * @param menuIds 用户授权ID
     * @return
     */
    Integer insertRole(Role role, String[] menuIds);

    /**
     * 根据角色状态查找角色列表
     * @param status 角色状态
     * @return List<Role>
     */
    List<Role> listBySataus(Integer status);

    /**
     * 根据分页信息/搜索内容查找角色列表
     * @param page 分页信息
     * @return
     */
    Page<Role> listByPage(Page<Role> page);



    /**
     * 根据角色ID删除角色,同时删除角色记录
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);
}

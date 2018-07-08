package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.RoleMenu;
import cn.vision.invicloud.support.entity.UserRole;
import cn.vision.invicloud.support.mapper.RoleMapper;
import cn.vision.invicloud.support.mapper.RoleMenuMapper;
import cn.vision.invicloud.support.mapper.UserRoleMapper;
import cn.vision.invicloud.support.service.IRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Integer insertRole(Role role, String[] menuIds) {

        // 创建角色
        Integer count = roleMapper.insert(role);

        // 遍历循环,插入角色目录表记录
        if (menuIds != null && menuIds.length > 0) {
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (int i = 0; i < menuIds.length; i++) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Integer.valueOf(menuIds[i]));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            }
            roleMenuMapper.insertRoleMenus(roleMenus);
        }

        return count;
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public BasePageDTO<Role> listByPage(PageInfo pageInfo, String search) {
        Page<Role> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<Role> roles = roleMapper.listByPage(pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new BasePageDTO<Role>(pageInfo, roles);
    }

    @Override
    public Integer updateStatus(Integer roleId) {
        return null;
    }

    @Override
    public Integer updateRole(Role role, String[] menuIds) {
        //更新用户信息
        Integer count = roleMapper.updateById(role);

        //先删除该用户授权信息
        RoleMenu queryRoleMenu = new RoleMenu();
        queryRoleMenu.setRoleId(role.getRoleId());
        roleMenuMapper.delete(new EntityWrapper<RoleMenu>(queryRoleMenu));

        //遍历循环,插入角色目录表记录
        if (menuIds != null && menuIds.length > 0) {
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (int i = 0; i < menuIds.length; i++) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Integer.valueOf(menuIds[i]));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            }
            roleMenuMapper.insertRoleMenus(roleMenus);
        }
        return count;
    }

    @Override
    public Integer deleteByRoleId(Integer roleId) {
        // 删除用户角色表中记录
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRoleMapper.delete(new EntityWrapper<UserRole>(userRole));

        // 删除角色目录表中记录
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenuMapper.delete(new EntityWrapper<RoleMenu>(roleMenu));

        // 删除角色表角色
        return roleMapper.deleteById(roleId);
    }
}

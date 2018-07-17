package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.RoleMenu;
import cn.vision.invicloud.support.mapper.RoleMenuMapper;
import cn.vision.invicloud.support.mapper.UserRoleMapper;
import cn.vision.invicloud.support.pojo.dto.RoleMenuDTO;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.service.IRoleMenuService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Set<String> getByRolesId(Set<String> roleIds) {
        Set<String> roleMenus = new HashSet<>();

        Set<RoleMenuDTO> menus = roleMenuMapper.listByRoleIds(roleIds);

        // 遍历权限列表
        for (RoleMenuDTO menu : menus) {
            if (StringUtils.isNotBlank(menu.getPermission())) {
                // 添加基于Permission的权限信息
                roleMenus.add(menu.getPermission());
            }
        }
        return roleMenus;
    }

    @Override
    public List<RoleMenuVO> listByUserId(Integer userId) {

        List<RoleMenuVO> menus = new ArrayList<>();

        List<Role> roles = userRoleMapper.listByUserId(userId);
        Set<Integer> roleIds = new HashSet<>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }

        // 查询一级目录
        List<RoleMenuDTO> parentAllMenus = roleMenuMapper.listByRoleIdsAndType(roleIds,1);
        List<RoleMenuDTO> parentMenus = menuDereplication(parentAllMenus);// 去重
        // 查询二级目录
        List<RoleMenuDTO> childAllMenus = roleMenuMapper.listByRoleIdsAndType(roleIds,2);
        List<RoleMenuDTO> childMenus = menuDereplication(childAllMenus);// 去重

        // 获取根级权限的子级权限
        for (RoleMenuDTO parentMenu : parentMenus) {
            recursionMenu(menus, childMenus, parentMenu);
        }

        return menus;
    }


    @Override
    public List<RoleMenuDTO> listRoleMenus() {
        return roleMenuMapper.listRoleMenus();
    }

    @Override
    public List<RoleMenuVO> listCheckedMenus(Integer roleId) {
        // 查询所有目录根据状态
        List<RoleMenuDTO> menus = roleMenuMapper.listRoleMenus();

        // 查找该角色拥有的权限
        RoleMenu queryRoleMenu = new RoleMenu();
        queryRoleMenu.setRoleId(roleId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(new EntityWrapper<RoleMenu>(queryRoleMenu));

        List<RoleMenuVO> roleMenuVOs = new ArrayList<>();

        // 遍历目录，设置该角色是否选中该目录
        for (RoleMenuDTO menu : menus) {
            RoleMenuVO roleMenuVO = new RoleMenuVO();
            BeanUtils.copyProperties(menu, roleMenuVO);

            for (RoleMenu roleMenu : roleMenus) {
                if (roleMenuVO.getMenuId().equals(roleMenu.getMenuId())) {
                    roleMenuVO.setChecked(true);
                }
            }
            roleMenuVOs.add(roleMenuVO);
        }
        return roleMenuVOs;
    }

    /**
     * DISTINCT用不来
     * @param sourceRoleMenuVOs 原权限
     * @return
     */
    private List<RoleMenuDTO> menuDereplication(List<RoleMenuDTO> sourceRoleMenuVOs) {
        List<RoleMenuDTO> roleMenuDTOs = new ArrayList<>();
        for (RoleMenuDTO roleMenuDTO : sourceRoleMenuVOs) {
            if (!roleMenuDTOs.contains(roleMenuDTO)) {
                roleMenuDTOs.add(roleMenuDTO);
            }
        }
        return roleMenuDTOs;
    }

    /**
     * 递归得到每个权限的子级权限
     * @param menus 处理后的目录列表
     * @param childMenus  二级目录列表
     * @param parentMenu 当前一级目录
     */
    private void recursionMenu(List<RoleMenuVO> menus, List<RoleMenuDTO> childMenus, RoleMenuDTO parentMenu) {
        List<RoleMenuDTO> childMenuList = new ArrayList<>();
        for (RoleMenuDTO menu : childMenus) {
            if (parentMenu.getMenuId().equals(menu.getParentId())) {
                childMenuList.add(menu);
            }
        }
        RoleMenuVO parentMenuVo = new RoleMenuVO();
        BeanUtils.copyProperties(parentMenu, parentMenuVo);
        parentMenuVo.setChildMenus(childMenuList);
        menus.add(parentMenuVo);
    }
}

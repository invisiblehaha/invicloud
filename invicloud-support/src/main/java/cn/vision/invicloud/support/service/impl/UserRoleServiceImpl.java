package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.UserRole;
import cn.vision.invicloud.support.mapper.UserRoleMapper;
import cn.vision.invicloud.support.pojo.dto.UserPageDTO;
import cn.vision.invicloud.support.pojo.dto.UserRoleDTO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.IUserRoleService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }

    @Override
    public UserPageDTO listByRoleId(Integer roleId, PageInfo pageInfo, String search) {
        Page<UserVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<UserVO> userVOs = userRoleMapper.listByRoleId(roleId, pageInfo, search, page);
        pageInfo.setTotal((int)page.getTotal());
        return new UserPageDTO(userVOs, pageInfo);
    }


    @Override
    public UserRoleDTO getByUserId(Integer userId) {

        List<Role> roles = userRoleMapper.listByUserId(userId);

        Set<String> roleSigns = new HashSet<>();
        Set<String> roleIds = new HashSet<>();

        // 遍历角色列表
        for (Role role : roles) {
            roleSigns.add(role.getRoleSign());
            roleIds.add(role.getRoleId().toString());
        }
        return new UserRoleDTO(roleSigns, roleIds);
    }

}

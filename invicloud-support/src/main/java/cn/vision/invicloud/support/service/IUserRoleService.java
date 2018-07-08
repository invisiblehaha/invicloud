package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.UserRole;
import cn.vision.invicloud.support.pojo.dto.UserPageDTO;
import cn.vision.invicloud.support.pojo.dto.UserRoleDTO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 根据用户ID查找角色列表
     * @param userId 用户ID
     * @return
     */
    List<Role> listByUserId(Integer userId);

    /**
     * 根据角色ID查找用户列表
     * @param roleId 角色ID
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @return
     */
    UserPageDTO listByRoleId(Integer roleId, PageInfo pageInfo, String search);

    /**
     * 根据用户ID查找角色列表
     * @param userId 管理员ID
     * @return
     */
    UserRoleDTO getByUserId(Integer userId);

}

package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.UserRole;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 插入用户角色记录
     * @param userRoles 用户角色记录信息
     * @return
     */
    Integer insertUserRoles(@Param("userRoles") List<UserRole> userRoles);

    /**
     * 根据用户ID查找角色列表
     * @param userId 用户ID
     * @return List<Role>
     */
    List<Role> listByUserId(@Param("userId") Integer userId);

    /**
     * 根据角色ID查找用户列表
     * @param roleId 角色ID
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @param rowBounds 分页实体
     * @return
     */
    List<UserVO> listByRoleId(@Param("roleId") Integer roleId, @Param("pageInfo") PageInfo pageInfo,
                              @Param("search") String search, RowBounds rowBounds);
}

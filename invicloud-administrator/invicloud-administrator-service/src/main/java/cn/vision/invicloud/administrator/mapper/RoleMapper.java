package cn.vision.invicloud.administrator.mapper;

import java.util.List;
import cn.vision.invicloud.administrator.entity.Role;

/**
 * @Author: Hattori
 * @Date: 2018/7/3 5:11
 * @Description:
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据分页信息/搜索内容查找角色列表
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @param rowBounds 分页实体
     * @return
     */
    List<Role> listByPage(@Param("pageInfo") PageInfo pageInfo, @Param("search") String search, RowBounds rowBounds);

}
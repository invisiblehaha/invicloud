package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询 : 根据state状态查询用户列表，分页显示
     *
     * @param page 翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @return
     */
    List<User> selectUserList(Pagination page);
}

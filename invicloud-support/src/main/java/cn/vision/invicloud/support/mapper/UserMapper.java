package cn.vision.invicloud.support.mapper;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID查找用户信息
     * @param userId 用户ID
     * @return
     */
    UserVO getById(@Param("userId") Integer userId);


    /**
     * 根据分页信息/搜索内容查找用户列表
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @param rowBounds 分页实体
     * @return
     */
    List<UserVO> listByPage(@Param("pageInfo") PageInfo pageInfo, @Param("search") String search, RowBounds rowBounds);

    /**
     * 根据userId更新用户信息
     * @return
     */
    Integer updateById(@Param("userId") Integer userId,@Param("loginName") String loginName,@Param("userName") String userName,@Param("sex") Integer sex,@Param("age") Integer age,@Param("telephone") String telephone);



    /*
     * 自己写的
     * */
    Integer deleteByUserId(@Param("userId") Integer userId);

    Integer updateByUserId(@Param("userId") Integer userId);
}

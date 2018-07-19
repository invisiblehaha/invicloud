package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.dto.UserPageDTO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IUserService extends IService<User> {

    /**
     * 创建用户以及插入角色记录
     * @param user 用户信息
     * @param roleIds 角色记录ID
     * @return
     */
    Integer insertUser(User user, String[] roleIds);

    /**
     * 根据用户账号查找用户
     * @param loginName 用户账号
     * @return
     */
    User getByLoginName(String loginName);

    /**
     * 根据用户ID查找用户信息
     * @param userId 用户ID
     * @return
     */
    UserVO getById(Integer userId);

    /**
     * 根据分页信息/搜索内容查找用户列表
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @return
     */
    UserPageDTO listByPage(PageInfo pageInfo, String search);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return
     */
    Integer updateByUserId(User user);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @param roleIds 角色记录ID
     * @return
     */
    Integer updateUser(User user, String[] roleIds);

    /**
     * 更新用户密码
     * @param nowPassword 密码
     * @param newPassword 新密码
     * @param userId 用户ID
     * @param userName 用户昵称
     * @return
     */
    Integer updatePsw(String nowPassword, String newPassword, Integer userId, String userName);


    /**
     * 根据用户ID删除用户,同时删除角色记录、登录日志
     * @param userId 用户ID
     * @return
     */
    Integer deleteByUserId(Integer userId);

    //返回返回即将被注册的userid
    Integer getLastestPlusCustomerId();
}

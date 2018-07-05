package cn.vision.invicloud.support.service;

import cn.vision.invicloud.support.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户账号查找用户
     * @param loginName 用户
     * @return
     */
    User getByLoginName(String loginName);

}

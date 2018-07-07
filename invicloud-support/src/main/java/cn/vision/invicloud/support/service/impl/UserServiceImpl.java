package cn.vision.invicloud.support.service.impl;

import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.mapper.UserMapper;
import cn.vision.invicloud.support.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Hattori
 * @since 2018-07-04
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByLoginName(String loginName) {
        User user=new User();
        user.setLoginName(loginName);
        return userMapper.selectOne(user);
    }

    @Override
    public Page<User> selectUserPage(Page<User> page, Integer state) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        // page.setOptimizeCountSql(false);
        // 不查询总记录数
        // page.setSearchCount(false);
        // 注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        return page.setRecords(userMapper.selectUserList(page));
    }
}

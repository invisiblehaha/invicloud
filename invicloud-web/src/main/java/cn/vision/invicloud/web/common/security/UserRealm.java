package cn.vision.invicloud.web.common.security;

import cn.vision.invicloud.web.common.enums.StatusEnum;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.service.IRoleMenuService;
import cn.vision.invicloud.support.service.IUserRoleService;
import cn.vision.invicloud.support.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Hattori
 * @Date: 2018/7/5 1:58
 * @Description:
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * Shiro登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.getByLoginName(token.getUsername());

        if (user == null) {
            throw new UnknownAccountException();// 没找到帐号
        }

        if (StatusEnum.FREEZE.getStatus().equals(user.getStatus())) {
            throw new DisabledAccountException();// 校验用户状态
        }

     // 认证缓存信息
        return new SimpleAuthenticationInfo(user, user.getLoginPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //TODO:不知道干什么的
        return null;
    }

}

package cn.vision.invicloud.web.common.utils;

import cn.vision.invicloud.support.entity.User;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * @Author: Hattori
 * @Date: 2018/7/5 2:18
 * @Description:
 */

public class LoginUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoginUtils.class);

    /**
     * 验证验证码
     * @param registerCode
     * @return
     */
    public static boolean validate(String registerCode, HttpSession session) {
        // 获取Session中验证码
        Object captcha = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        boolean result = registerCode.equalsIgnoreCase(captcha.toString());
        if (result)
            session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        return result;
    }

    /**
     * 获取登录用户
     * @return
     */
    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user;
    }

    /**
     * 获取登录用户ID
     * @return
     */
    public static Integer getUserId() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user != null) return user.getUserId();
        else return null;
    }
}
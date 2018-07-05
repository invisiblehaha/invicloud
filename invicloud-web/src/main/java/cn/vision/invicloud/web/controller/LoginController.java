package cn.vision.invicloud.web.controller;

import cn.vision.invicloud.support.common.enums.CommonReturnCode;
import cn.vision.invicloud.support.common.enums.LoginReturnCode;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Date;


/**
 * @Author: Hattori
 * @Date: 2018/7/4 4:12
 * @Description:
 */
@Controller
public class LoginController{

    protected Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private Producer captchaProducer;
    //TODO:和下面的不用HttpSession对应
    @Autowired
    private HttpServletRequest request;


    /**
     * GET 登录页面
     * @param model
     * @return
     */
    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "/login/login";
    }

    /**
     * POST 登录信息
     * @param loginName
     * @param loginPassword
     * @param registerCode
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Object login(@RequestParam("loginName") String loginName,
                        @RequestParam("loginPassword") String loginPassword, @RequestParam("registerCode") String registerCode) {
        //TODO:把Httpsession改成session，那样就不用Httpsession了.
        if (!LoginUtils.validate(registerCode,request.getSession())) {
            return LoginReturnCode.REGISTER_CODE_ERROR;
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPassword);
        try{
            currentUser.login(token);
            return CommonReturnCode.SUCCESS;
        } catch (UnknownAccountException e) {
            logger.error(LoginReturnCode.USER_NOT_EXIST.getMessage(), e);
            return LoginReturnCode.USER_NOT_EXIST;
        } catch (IncorrectCredentialsException e) {
            logger.error(LoginReturnCode.WRONG_PASSWORD.getMessage(), e);
            return LoginReturnCode.WRONG_PASSWORD;
        } catch (ExcessiveAttemptsException e) {
            logger.error(LoginReturnCode.USER_LOCK.getMessage(), e);
            return LoginReturnCode.USER_LOCK;
        }catch (RuntimeException e) {
            logger.error(CommonReturnCode.UNKNOWN_ERROR.getMessage(), e);
            return CommonReturnCode.UNKNOWN_ERROR;
        }
    }

    /**
     * GET 未授权
     * @return
     */
    @GetMapping(value = "/unauth")
    public String unauth() {
        //TODO:未授权界面
        return "redirect:/login";
    }

    /**
     * GET 退出
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    /**
     * GET 验证码
     */
    @GetMapping(value = "/captcha-image")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        // 生成验证码，保存在会话中
        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}


package cn.vision.invicloud.web.controller.system;

import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.enums.LoginReturnCode;
import cn.vision.invicloud.web.common.enums.RegisterReturnCode;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import humanfaceAPI.*;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Base64;


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

    @Autowired
    private IUserService userService;



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
    public Object login(@RequestParam(required = false,value = "loginName") String loginName,
                        @RequestParam(required = false,value = "loginPassword") String loginPassword,
                        @RequestParam(required = false,value = "registerCode") String registerCode,
                        @RequestParam(required = false,value = "imgString") String imgStirng) {
        if(imgStirng == null)
        {
            if (!LoginUtils.validate(registerCode,request.getSession())) {
                return new WebResult(LoginReturnCode.REGISTER_CODE_ERROR);
            }
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPassword);
            try{
                currentUser.login(token);
                return new WebResult(CommonReturnCode.SUCCESS);
            } catch (UnknownAccountException e) {
                logger.error(LoginReturnCode.USER_NOT_EXIST.getMessage(), e);
                return new WebResult(LoginReturnCode.USER_NOT_EXIST);
            } catch (IncorrectCredentialsException e) {
                logger.error(LoginReturnCode.WRONG_PASSWORD.getMessage(), e);
                return new WebResult(LoginReturnCode.WRONG_PASSWORD);
            } catch (ExcessiveAttemptsException e) {
                logger.error(LoginReturnCode.USER_LOCK.getMessage(), e);
                return new WebResult(LoginReturnCode.USER_LOCK);
            }catch (RuntimeException e) {
                logger.error(CommonReturnCode.UNKNOWN_ERROR.getMessage(), e);
                return new WebResult(CommonReturnCode.UNKNOWN_ERROR);
            }
        }
        else
        {
            byte[] buff = Base64ToByteArr(imgStirng);
            String faceDetectResult = InterfaceOfAllAPIs.searchForUserId(buff,"FS_0");

            if(faceDetectResult==Key.KEY_FOR_DETECT_FAILED_MESSAGE)
            {
                return new WebResult(RegisterReturnCode.REGISTER_FACE_NOT_DETECTED);
            }
            if(faceDetectResult == Key.KEY_FOR_SEARCH_MATCHFAILED_MESSAGE)
            {
                return new WebResult(RegisterReturnCode.REGISTER_FACE_NOT_SEARCHED);
            }
            else
            {
                UserVO userVO = userService.getById(Integer.parseInt(faceDetectResult));
                String userLoginName = userVO.getLoginName();

                Subject currentUser = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userLoginName, "1");
                try{
                    currentUser.login(token);
                    return new WebResult(CommonReturnCode.SUCCESS);
                }catch (UnknownAccountException e) {
                    logger.error(LoginReturnCode.USER_NOT_EXIST.getMessage(), e);
                    return new WebResult(LoginReturnCode.USER_NOT_EXIST);
                }

            }
        }
    }
    final Base64.Decoder decoder = Base64.getDecoder();
    public byte[] Base64ToByteArr(String b64)
    {
        String[] strArr=b64.split(",");
        return decoder.decode(strArr[1]);
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


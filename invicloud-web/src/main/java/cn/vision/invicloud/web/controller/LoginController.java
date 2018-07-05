package cn.vision.invicloud.web.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;


/**
 * @Author: Hattori
 * @Date: 2018/7/4 4:12
 * @Description:
 */
@Controller
public class LoginController{

    @Autowired
    private Producer captchaProducer;

    /**
     * GET 登录页面
     * @param model
     * @return
     */
    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "";
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
        //:TODO
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


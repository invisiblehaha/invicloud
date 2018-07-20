package cn.vision.invicloud.web.controller.shopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WelcomeController {

    /**
     * 初始化文章页面
     *
     * @param beginDate
     * @param endDate
     * @param keyword
     * @return
     */
    @RequestMapping("")
    public ModelAndView init(String beginDate, String endDate, String keyword) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/shopping/shopping/login");
        //重定向
        return mav;
    }
}

package cn.vision.invicloud.web.controller;

import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.IRoleMenuService;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 2:54
 * @Description:
 */
@Controller
public class FrontController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * GET 首页
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    /**
     * GET 首页/操作中心
     * @return
     */
    @GetMapping(value = "/index")
    public String index(Model model) {
        // 用户信息
        UserVO user = userService.getById(LoginUtils.getUserId());
        model.addAttribute("user", user);
        // 系统目录
        List<RoleMenuVO> menus = roleMenuService.listByUserId(LoginUtils.getUserId());
        model.addAttribute("menus", menus);

        return "/index";
    }
}



package cn.vision.invicloud.web.controller;

import cn.vision.invicloud.support.entity.Menu;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.vo.MenuVO;
import cn.vision.invicloud.support.service.IRoleMenuService;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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
     *
     * @return
     */
    @GetMapping("/")
    public String getIndex(Model model) {
        return "redirect:/index";
    }

    /**
     * GET 首页
     *
     * @return
     */
    @GetMapping(value = "/index")
    public String index(Model model) {
        // 用户信息
        User user = userService.getById(LoginUtils.getUserId());
        model.addAttribute("user", user);
        // 目录
        List<MenuVO> menus = roleMenuService.listByRoleId(LoginUtils.getUserId());
        model.addAttribute("menus", menus);

        return "";
    }
}



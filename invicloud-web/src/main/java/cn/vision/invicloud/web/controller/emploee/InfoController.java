package cn.vision.invicloud.web.controller.emploee;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.service.IUserRoleService;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.enums.StatusEnum;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 4:17
 * @Description:
 */
@Controller
@RequestMapping(value = "/administrator/info")
public class InfoController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * GET 个人信息页面
     * @return
     */
    @GetMapping(value = "/view")
    public String getInfoPage(Model model) {
        // 个人信息
        User user = userService.getById(LoginUtils.getUserId());
        model.addAttribute("user", user);

        // 角色
        List<Role> roles = userRoleService.listByUserId(LoginUtils.getUserId(),StatusEnum.NORMAL.getStatus());
        model.addAttribute("roles", roles);

        return "";
    }

    /**
     * PUT 更新个人信息
     * @return
     */
    @PutMapping(value = "/edit")
    @ResponseBody
    public Object updateUser(User user) {
        if (user != null) {
            user.setUserId(LoginUtils.getUserId());
            int count = userService.updateByUserId(user);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * POST 修改管理员密码
     * @return
     */
    @PutMapping(value = "/edit/psw")
    @ResponseBody
    public Object updatePwd(@RequestParam("nowPassword") String nowPassword,@RequestParam("newPassword") String newPassword) {
        User user= LoginUtils.getUser();
        if (user != null) {
            Integer count = userService.updatePassword(user.getUserName(),nowPassword, newPassword);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }
}
package cn.vision.invicloud.web.controller.emploee;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.IUserRoleService;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.utils.LoginUtils;
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
@RequestMapping(value = "/user/profile")
public class InfoController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * GET 用户个人信息
     * @return
     */
    @GetMapping(value = "/view")
    public String getInfoPage(Model model) {

        // 用户信息
        UserVO userVO = userService.getById(LoginUtils.getUserId());
        model.addAttribute("user", userVO);

        // 用户权限
        List<Role> roles = userRoleService.listByUserId(LoginUtils.getUserId());
        model.addAttribute("roles", roles);

        return "";
    }

    /**
     * PUT 更新用户信息
     * @return
     */
    @PutMapping(value = "/edit")
    @ResponseBody
    public Object updateUser(User user) {
        if (LoginUtils.getUser() != null) {
            user.setUserId(LoginUtils.getUser().getUserId());
            int count = userService.updateByUserId(user);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * POST 修改用户密码
     * @return
     */
    @PutMapping(value = "/edit/psw")
    @ResponseBody
    public Object updatePwd(@RequestParam("nowPassword") String nowPassword,@RequestParam("newPassword") String newPassword) {
        //希望传进来的没问题
        User user = LoginUtils.getUser();
        if (user != null) {
                Integer count = userService.updatePsw(nowPassword, newPassword, user.getUserId(),
                        user.getUserName());
                if(count!=-1)
                return new WebResult(CommonReturnCode.SUCCESS);
        }
            return new WebResult(CommonReturnCode.UNAUTHORIZED);

    }
}
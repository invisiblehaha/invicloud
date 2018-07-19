package cn.vision.invicloud.web.controller.emploee;

import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 4:17
 * @Description:
 */
@Controller
@RequestMapping(value = "/system/info")
@SessionAttributes(value = {"menus","user"})
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
    public String getInfoPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user, Model model, HttpSession session) {

        // 用户权限
        List<Role> roles = userRoleService.listByUserId(LoginUtils.getUserId());
        model.addAttribute("roles", roles);
        return "/system/info";
    }

    /**
     * PUT 更新用户信息
     * @return
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public Object updateUser(@RequestParam("login") String loginName,
                             @RequestParam("real") String realName, @RequestParam("sex") String sex,@RequestParam("age") Integer age, @RequestParam("tel") String tel, Model model) {
        User user=LoginUtils.getUser();
        if ( user!= null) {
            user.setLoginName(loginName);
            user.setUserName(realName);
            user.setAge(age);
            user.setTelephone(tel);
            Integer sexnum;
            if(sex.equals("男")){
                sexnum=1;
            }else{
                sexnum=2;
            }
            user.setSex(sexnum);
            int count = userService.updateByUserId(user);
            User newuser=userService.getById(user.getUserId());
            model.addAttribute("user",newuser);
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
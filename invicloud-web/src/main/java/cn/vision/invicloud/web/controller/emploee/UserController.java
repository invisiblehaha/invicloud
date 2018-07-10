package cn.vision.invicloud.web.controller.emploee;

import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.dto.UserPageDTO;
import cn.vision.invicloud.support.service.IRoleService;
import cn.vision.invicloud.support.service.IUserRoleService;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.web.common.WebPageResult;
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
 * @Date: 2018/7/7 4:14
 * @Description:
 */
@Controller
@RequestMapping(value = "/user/list")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * GET 用户列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/view")
    public String getListPage(Model model) {
        return "";
    }

    /**
     * GET 用户列表
     *
     * @return
     */
    @GetMapping(value = "/")
    @ResponseBody
    public Object listUser(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        UserPageDTO userPageDTO = userService.listByPage(pageInfo, search);
        return new WebPageResult(userPageDTO.getUserVOs(), userPageDTO.getPageInfo().getTotal());
    }

    /**
     * GET 个人资料
     *
     * @return
     */
    @GetMapping(value = "/{userId}")
    public String detail(Model model, @PathVariable Integer userId) {
        // 用户信息
        User user = userService.getById(userId);
        model.addAttribute("user", user);

        // 分配角色
        List<Role> roles = userRoleService.listByUserId(user.getUserId());
        model.addAttribute("roles", roles);// 用户权限

        return "";
    }

    /**
     * DELETE 删除用户
     *
     * @return
     */
    @DeleteMapping(value = "/{userId}")
    @ResponseBody
    public Object delete(@PathVariable("userId") Integer userId) {
        if (LoginUtils.getUser() != null) {
            Integer count = userService.deleteByUserId(userId);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 创建用户页面
     *
     * @return
     */
    @GetMapping(value = "/create")
    public String getInsertPage(Model model) {
        // 用户权限
        List<Role> roles = roleService.list();
        model.addAttribute("roles", roles);

        return "";
    }

    /**
     * POST 创建用户
     *
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public Object insert(User user, @RequestParam(required = false, value = "roleId") String[] roleIds) {

        if (LoginUtils.getUser() != null) {
            // 创建用户及插入角色记录
            Integer count = userService.insertUser(user, roleIds);
            if (count != -1)
                return new WebResult(CommonReturnCode.SUCCESS);

        }
        return new WebResult(CommonReturnCode.UNAUTHORIZED);

    }

    /**
     * GET 更新用户页面
     *
     * @return
     */
    @GetMapping(value = "/{userId}/edit")
    public String getUpdatePage(Model model, @PathVariable("userId") Integer userId) {
        // 用户信息
        User user = userService.getById(userId);
        model.addAttribute("user", user);

        // 用户权限
        List<Role> roles = roleService.list();
        model.addAttribute("roles", roles);

        // 分配角色
        List<Role> userRoles = userRoleService.listByUserId(user.getUserId());
        model.addAttribute("userRoles", userRoles);

        return "";
    }

    /**
     * PUT 更新用户信息
     *
     * @return
     */
    @PutMapping(value = "/{userId}")
    @ResponseBody
    public Object update(User user, @PathVariable("userId") Long userId,
                         @RequestParam(required = false, value = "roleId") String[] roleIds) {
        if (LoginUtils.getUser() != null) {
            // 更新用户及角色记录
            Integer count = userService.updateUser(user, roleIds);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }
}
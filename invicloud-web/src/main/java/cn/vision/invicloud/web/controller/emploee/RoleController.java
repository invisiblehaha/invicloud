package cn.vision.invicloud.web.controller.emploee;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.*;
import cn.vision.invicloud.support.pojo.dto.RoleMenuDTO;
import cn.vision.invicloud.support.pojo.dto.UserPageDTO;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.service.IRoleMenuService;
import cn.vision.invicloud.support.service.IRoleService;
import cn.vision.invicloud.support.service.IUserRoleService;
import cn.vision.invicloud.web.common.WebPageResult;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 4:13
 * @Description:
 */

@Controller
@RequestMapping(value = "/user/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * GET 角色列表页面
     * @param model
     * @return
     */
    @GetMapping(value = "/view")
    public String getRolePage(Model model) {
        return "";
    }

    /**
     * GET 角色列表
     * @param pageInfo
     * @param search
     * @return
     */
    @GetMapping(value = "/")
    @ResponseBody
    public Object listRole(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        BasePageDTO<Role> basePageDTO = roleService.listByPage(pageInfo, search);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }

    /**
     * GET 角色分类下用户列表页面
     * @param roleId
     * @return
     */
    @GetMapping(value = "/{roleId}/list")
    public String list(Model model, @PathVariable("roleId") Long roleId) {
        model.addAttribute("roleId", roleId);
        return "";
    }

    /**
     * GET 角色分类下用户列表
     * @return
     */
    @GetMapping(value = "/{roleId}/lists")
    @ResponseBody
    public Object listLogs(@PathVariable("roleId") Integer roleId, PageInfo pageInfo,
                           @RequestParam(required = false, value = "search") String search) {

        if (LoginUtils.getUser() != null) {
            UserPageDTO userPageDTO = userRoleService.listByRoleId(roleId, pageInfo, search);
            return new WebPageResult(userPageDTO.getUserVOs(),
                    userPageDTO.getPageInfo().getTotal());
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * DELETE 删除角色
     * @param roleId 角色ID
     * @return
     */
    @DeleteMapping(value = "/{roleId}")
    @ResponseBody
    public Object delete(@PathVariable("roleId") Integer roleId) {
        if (LoginUtils.getUser() != null) {
            Integer count = roleService.deleteByRoleId(roleId);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 创建角色页面
     * @return
     */
    @GetMapping(value = "/create")
    public String getInsertPage(Model model) {
        List<RoleMenuDTO> menus = roleMenuService.listRoleMenus();
        model.addAttribute("menus", JSON.toJSON(menus));
        return "";
    }

    /**
     * POST 创建角色
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public Object insert(Role role, @RequestParam(required = false, value = "menuIds") String menuId) {

        String[] menuIds = menuId.split(",");

        User user = LoginUtils.getUser();
        if (user != null) {
            Integer count = roleService.insertRole(role, menuIds);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 更新角色页面
     * @return
     */
    @GetMapping(value = "/{roleId}/edit")
    public String getUpdatePage(Model model, @PathVariable("roleId") Integer roleId) {
        // 目录是否选中
        List<RoleMenuVO> menus = roleMenuService.listCheckedMenus(roleId);
        model.addAttribute("menus", JSON.toJSON(menus));

        // 角色信息
        Role role = roleService.selectById(roleId);
        model.addAttribute("role", role);

        return "";
    }

    /**
     * PUT 更新角色信息
     * @return
     */
    @PutMapping(value = "/{roleId}")
    @ResponseBody
    public Object update(Role role, @PathVariable("roleId") Long roleId,
                         @RequestParam(required = false, value = "menuIds") String menuId) {
        String[] menuIds = menuId.split(",");

        User user = LoginUtils.getUser();
        if (user != null) {
            // 更新用户及角色记录
            Integer count = roleService.updateRole(role, menuIds);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }
}

package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.service.ICategoryService;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 2:24
 * @Description:
 */
@Controller
@RequestMapping(value = "/product/category")
public class ProductController{

    @Autowired
    private ICategoryService categoryService;

    /**
     * GET 分类管理页面
     * @return
     */
    @GetMapping(value = "/view")
    public String getAdvertPage(Model model) {
        return "";
    }

    /**
     * GET 更新类目页面
     * @return
     */
    @GetMapping(value = "/{categoryId}/edit")
    public String getUpdatePage(Model model, @PathVariable("categoryId") Long categoryId) {
        // 类目信息
        Category category = categoryService.selectById(categoryId);
        model.addAttribute("category", category);
        // 上级类目信息
        Category parentCategory = categoryService.selectById(category.getParentId());
        model.addAttribute("parentCategory", parentCategory);

        return "/modules/category/product_category_update";
    }

    /**
     * PUT 更新类目信息
     * @return
     */
    @PutMapping(value = "/{categoryId}")
    @ResponseBody
    public Object update(Category category, @PathVariable("categoryId") Integer categoryId) {
        if (LoginUtils.getUser() != null) {
            // 更新用户及类目记录
            Integer count = categoryService.updateCategory(category);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 创建类目页面
     * @return
     */
    @GetMapping(value = "/{categoryId}/create")
    public String getCreatePage(Model model, @PathVariable("categoryId") Integer categoryId) {
        // 类目信息
        Category category = categoryService.selectById(categoryId);
        model.addAttribute("category", category);
        return "";
    }

    /**
     * POST 创建类目
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public Object insert(Category category) {
        if (LoginUtils.getUser() != null) {
            boolean count = categoryService.insert(category);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }
}

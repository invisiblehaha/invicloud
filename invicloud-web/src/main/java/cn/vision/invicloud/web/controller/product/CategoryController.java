package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.ICategoryService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 2:24
 * @Description:
 */
@Controller
@RequestMapping(value = "/product/category")
@SessionAttributes(value = {"menus","user"})
public class CategoryController {


    @Autowired
    private ICategoryService categoryService;

    /**
     * GET 分类管理页面
     * @return
     */
    @GetMapping(value = "/view")
    public String getAdvertPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user) {
        return "/product/product_category_list";
    }

    /**
     * GET 分类列表,根据父类目ID
     * @return
     */
    @GetMapping(value = "/gid/{parentId}")
    @ResponseBody
    public Object listAdvert(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search,
                             @PathVariable("parentId") Integer parentId) {
        BasePageDTO<Category> basePageDTO = categoryService.listParentByPage(pageInfo, search, parentId);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }

}

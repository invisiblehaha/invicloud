package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.entity.Role;
import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.pojo.dto.ProductPageDTO;
import cn.vision.invicloud.support.pojo.dto.UserPageDTO;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import cn.vision.invicloud.support.service.ICategoryService;
import cn.vision.invicloud.support.service.IProductService;
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
@RequestMapping(value = "/product/product")
public class ProductController {


    @Autowired
    private IProductService productService;

    /**
     * GET 用户列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/view")
    public String getListPage(Model model) {
        return "/product/product_item";
    }



 /*   @RequestMapping("/getProductByID")
    @ResponseBody
    public ProductVO getProductByID(int productId, Model model)
    {
        ProductVO product = productService.getById(productId);
        return product;
    }*/


    //得到产品列表
    @GetMapping(value = "/")
    @ResponseBody
    public Object listProduct(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        ProductPageDTO productPageDTO = productService.listByPage(pageInfo, search);
        return new WebPageResult(productPageDTO.getProductVOs(), productPageDTO.getPageInfo().getTotal());
    }

    /**
     * GET 个人资料
     *
     * @return
     */
    @GetMapping(value = "/{productId}")
    public String detail(Model model, @PathVariable Integer productId) {
        // 用户信息
        ProductVO productVO = productService.getById(productId);
        model.addAttribute("product", productVO);



        return "";
    }


}

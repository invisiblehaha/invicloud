package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.IProductService;
import cn.vision.invicloud.web.common.WebPageResult;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/product/product")
@SessionAttributes(value = {"menus","user"})
public class ProductController {
    @Autowired
    private IProductService productService;
    /**
     * GET 商品管理页面
     * @return
     */
    @GetMapping(value = "/view")
    public String list(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user) {
        return "/product/product";
    }

    @GetMapping(value = "/")
    @ResponseBody
    public Object listProduct(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        BasePageDTO<Product> basePageDTO = productService.list(pageInfo, search);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }



    /**
     * DELETE 删除商品
     *
     * @return
     */
    @DeleteMapping(value = "/{productId}")
    @ResponseBody
    public Object delete(@PathVariable("productId") Integer productId) {
        if (productService.getById(productId) != null) {
            Integer count = productService.deleteByProductId(productId);
            return new WebResult(CommonReturnCode.SUCCESS);
        } else {
            return new WebResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

   @PostMapping(value = "/instinct")
    @ResponseBody
    public Object insertInto(Integer  productId, String productName,Double productPrice,Integer stock ) {

        Integer i = productService.insertInto(productName,BigDecimal.valueOf(productPrice),stock);
        return i;
    }

}

package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.service.IProductService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/product/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    /**
     * GET 商品管理页面
     * @return
     */
    @GetMapping(value = "/view")
    public String list(Model model) {
        return "/product/product";
    }

    @GetMapping(value = "/")
    @ResponseBody
    public Object listProduct(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        BasePageDTO<Product> basePageDTO = productService.list(pageInfo, search);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }


}

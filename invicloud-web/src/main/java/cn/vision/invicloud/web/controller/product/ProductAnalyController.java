package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/product/productPredict")
@SessionAttributes(value = {"menus","user"})
public class ProductAnalyController {
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/view")
    public String list(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user,@RequestParam("id") String productId, Model model ){
        productService.singProduct(Integer.parseInt(productId));
        return "/product/productPredict";
    }
}

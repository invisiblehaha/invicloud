package cn.vision.invicloud.web.controller.recommend;

import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.ICustomerService;
import cn.vision.invicloud.support.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/recommend/product")
@SessionAttributes(value = {"menus","user"})
public class ProductDemoController {
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/view")
    public String getProductDemoPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user, @RequestParam("customerId")Integer customerId, Model model, @RequestParam("id") Integer id){
        model.addAttribute("customerId",customerId);
        model.addAttribute("product",productService.getById(id));
        return "/recommend/productDetail";
    }


}

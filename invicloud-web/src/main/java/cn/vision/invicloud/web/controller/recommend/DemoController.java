package cn.vision.invicloud.web.controller.recommend;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.pojo.vo.*;
import cn.vision.invicloud.support.service.ICustomerService;
import cn.vision.invicloud.support.service.IOrderAnalyService;
import cn.vision.invicloud.support.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/recommend/demo")
@SessionAttributes(value = {"menus","user"})
public class DemoController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOrderAnalyService analyService;
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/view")
    public String getDemoPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user, @RequestParam("customerId")Integer customerId,Model model){
        Customer customer=customerService.getBycustomerId(customerId);
        model.addAttribute("customer",customer);
        String file=System.getProperty("user.dir")+"/consumptionLevel.txt";
        List<LevelVO> levellist=analyService.getLevels(file);

        for (LevelVO o:levellist) {
            if(o.getCustomerId().equals(customerId)){
                model.addAttribute("averageMoney",o.getMoney());
                model.addAttribute("averageNum",o.getNum());
            }
        }
        String file2=System.getProperty("user.dir")+"/recommendationByProductCF.txt";

        List<LikeVO> likelist=analyService.getLikes(file2);
        for(LikeVO o:likelist){
            if(o.getCustomerId().equals(customerId)){
                List<Integer> products=o.getLikeList();
                model.addAttribute("likeList",products);
                List<ProductVO> goods=new ArrayList<ProductVO>();
                for(Integer i:products){
                ProductVO productVO=productService.getById(i);
                goods.add(productVO);
                }
                model.addAttribute("goods",goods);
            }
        }

        return "/recommend/demo";
    }

}

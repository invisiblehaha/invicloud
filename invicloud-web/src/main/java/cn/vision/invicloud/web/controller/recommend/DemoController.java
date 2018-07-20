package cn.vision.invicloud.web.controller.recommend;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.entity.Product;
import cn.vision.invicloud.support.pojo.vo.*;
import cn.vision.invicloud.support.service.*;
import cn.vision.invicloud.web.common.WebPageResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
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
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping(value = "/view")
    public String getDemoPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user, @RequestParam("customerId")Integer customerId,Model model){
        Customer customer=customerService.getBycustomerId(customerId);
        model.addAttribute("customer",customer);


        System.out.println(System.getProperty("user.dir"));

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
        List<ProductVO> productVOList=productService.getAllProduct();

        List<CatAnalyVO> catList=analyService.getCatList(customerId);
        model.addAttribute("products",productVOList);
        Map<Integer, String> map=new HashMap<>();
        int total=0;
        for (CatAnalyVO catAnalyVO:catList) {
        total+=catAnalyVO.getBuyTotal();
        }
        DecimalFormat df = (DecimalFormat) NumberFormat.getPercentInstance();
        df.applyPattern("0.00%");
        for (CatAnalyVO catAnalyVO:catList) {
        map.put(catAnalyVO.getCategoryId(),df.format((double)catAnalyVO.getBuyTotal()/(double)total));
        model.addAttribute("catmap",map);
        }
        return "/recommend/demo";
    }
// @PostMapping(value = "/search")
//    @ResponseBody
//    public List<ProductVO> searchForProducts(@RequestParam(required = false)String userInput,Model model)
//    {
//        List<ProductVO> productVOList=productService.getProductBySearch(userInput);
//        for(ProductVO o:productVOList)
//        {
//            System.out.println(o.getProductName());
//        }
//        return productVOList;
//    }
//
//    @PostMapping(value = "/cate")
//    @ResponseBody
//    public Object getCategory()
//    {
//        JSONObject obj=new JSONObject();
//        List<Category> categoryList=categoryService.listLowerCategories(0);
//        obj.put("0",categoryList);
//        for(Category o:categoryList)
//        {
//            o.getCategoryId();
//            List<Category> categoryList1=categoryService.listLowerCategories(o.getCategoryId());
//            obj.put(o.getCategoryId().toString(),categoryList1);
//        }
//        return obj;
//    }
//
//    @PostMapping(value = "/cateProduct")
//    @ResponseBody
//    public Object getCateProduct(String id)
//    {
//        List<ProductVO> productVOList=productCategoryService.getCateProduct(id);
//        for(ProductVO o:productVOList)
//        {
//            System.out.println(o.getProductName());
//        }
//        return productVOList;
//    }
}

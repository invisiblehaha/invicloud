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
    private IEmotionRecordService emotionRecordService;

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
        model.addAttribute("products",productVOList);

        //购买历史饼图
        List<CatAnalyVO> catList=analyService.getCatList(customerId);
        Map<String, Double> map= new HashMap<>();
        int total=0;
        for (CatAnalyVO catAnalyVO:catList) {
            total+=catAnalyVO.getBuyTotal();
        }
//        DecimalFormat df = (DecimalFormat) NumberFormat.getPercentInstance();
//        df.applyPattern("0.00%");
        String name="";
        double percentage = 0;
        for (CatAnalyVO catAnalyVO:catList) {
            name = categoryService.getById(catAnalyVO.getCategoryId()).getCategoryName();
            percentage = (double) catAnalyVO.getBuyTotal() / (double) total;
            map.put(name, percentage);
            model.addAttribute("catmap", map);
        }

        //心情历史饼图
        List<EmotionAnalyVO> EList=emotionRecordService.getEList(customerId);
        Map<String, Double> map2= new HashMap<>();
        int total2=0;
        for(EmotionAnalyVO emotionAnalyVO:EList){
            total+=emotionAnalyVO.getNum();
        }
        for(EmotionAnalyVO emotionAnalyVO:EList){
            percentage = (double)emotionAnalyVO.getNum()/(double)total2;
            map.put(emotionAnalyVO.getEmotion(),percentage);
            model.addAttribute("emap",map2);
        }
        return "/recommend/demo";
    }

}

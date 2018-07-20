package cn.vision.invicloud.web.controller.shopping;

import cn.vision.invicloud.support.entity.Category;
import cn.vision.invicloud.support.pojo.vo.CustomerVO;
import cn.vision.invicloud.support.pojo.vo.ProductVO;
import cn.vision.invicloud.support.service.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import cn.vision.invicloud.support.service.ICustomerService;

import cn.vision.invicloud.web.common.WebPageResult;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.RegisterReturnCode;
import humanfaceAPI.*;




@Controller
@RequestMapping(value = "/shopping/shopping")
public class ShoppingController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderProductService orderProductService;
    @Autowired
    private  ICustomerService customerService;

    @GetMapping(value = "/login")
    public String getShoppingLoginPage(Model model) {
        return "/shopping/shoppingLogin";
    }

    @GetMapping(value = "/view")
    public String getShoppingPage(@RequestParam(name="loginId",required = false)Integer loginId,@RequestParam(name="productId",required = false)Integer productId, Model model) {
        List<ProductVO> productVOList=productService.getAllProduct();
        model.addAttribute("products",productVOList);
        return "/shopping/shopping";
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public List<ProductVO> searchForProducts(@RequestParam(required = false)String userInput,Model model)
    {
        List<ProductVO> productVOList=productService.getProductBySearch(userInput);
        for(ProductVO o:productVOList)
        {
            System.out.println(o.getProductName());
        }
        return productVOList;
    }

    @PostMapping(value = "/cate")
    @ResponseBody
    public Object getCategory()
    {
        System.out.println("!");
        JSONObject obj=new JSONObject();
        List<Category> categoryList=categoryService.listLowerCategories(0);
        obj.put("0",categoryList);
        for(Category o:categoryList)
        {
            o.getCategoryId();
            List<Category> categoryList1=categoryService.listLowerCategories(o.getCategoryId());
            obj.put(o.getCategoryId().toString(),categoryList1);
        }
        return obj;
    }

    @PostMapping(value = "/cateProduct")
    @ResponseBody
    public Object getCateProduct(String id,Integer flag)
    {
        if(flag==0) {
            List<ProductVO> productVOList = productCategoryService.getCateProduct(id);
            return productVOList;
        }
        else{
            List<ProductVO> productVOList=new ArrayList<ProductVO>();
            List<ProductVO> productVOList1=productCategoryService.getCateProduct(id);
            for(ProductVO o:productVOList1){
                productVOList.add(o);
            }
            List<Category> categoryList=categoryService.listLowerCategories(Integer.parseInt(id));
            for(Category o:categoryList){
                List<ProductVO> tmpList = productCategoryService.getCateProduct(o.getCategoryId().toString());
                for(ProductVO v:tmpList){
                    productVOList.add(v);
                }
            }
            return productVOList;
        }
    }

    @GetMapping(value = "/productDetail")
    public String getProductDetail(@RequestParam("productId")Integer productId, @RequestParam(name="customerId",required = false)Integer customerId, Model model) {
        ProductVO productVO=productService.getById(productId);
        model.addAttribute("product",productVO);
        return "/shopping/productDetail";
    }

    @PostMapping(value = "/addOrderToDB")
    @ResponseBody
    public Long addOrder(@RequestParam("customerId")String customerId,@RequestParam("productAccount")String productAccount,@RequestParam("productTotalPrice")String productTotalPrice)
    {
        System.out.println("1111号订单,客户id:"+customerId+",商品总数:"+productAccount+",商品总价:"+productTotalPrice);
        Long orderId=orderService.addOrderToDB(customerId,"1",productAccount,productTotalPrice);
        System.out.println("!!!!!!"+orderId);
        return orderId;
    }

    @PostMapping(value = "/addOrderItemToDB")
    @ResponseBody
    public void addOrderItem(@RequestParam("orderId")String orderId,@RequestParam("productId")String productId,@RequestParam("productNumber")String productNumber,@RequestParam("productPrice")String productPrice)
    {
        System.out.println("订单号:"+orderId+"商品Id"+productId+"商品数量"+productNumber+"此类商品总价格"+productPrice);
        orderProductService.addOrderItemToDB(orderId,productId,productNumber,productPrice);
    }

//    @PostMapping(value = "/checkLoginId")
//    @ResponseBody
//    public CustomerVO checkLoginId(@RequestParam("loginId")String loginId){
//        CustomerVO customerVO=customerService.getById(Integer.parseInt(loginId));
//        return customerVO;
//    }


    @PostMapping(value = "/checkLogin")
    @ResponseBody
    public Object listCustomer(@RequestParam(required = false, value = "imgString")String b64String) {
        String id=null;
        if(b64String!=null) {
            System.out.println(b64String);
            byte[] buff = Base64ToByteArr(b64String);
            id = InterfaceOfAllAPIs.searchForUserId(buff, "FS_1");
            String detectToken = InterfaceOfAllAPIs.detect(buff);
            System.out.println(detectToken);
            if(id==Key.KEY_FOR_SEARCH_MATCHFAILED_MESSAGE)
            {
                return new WebResult(RegisterReturnCode.REGISTER_FACE_NOT_SEARCHED);

            }
            if(id==Key.KEY_FOR_DETECT_FAILED_MESSAGE)
            {
                return new WebResult(RegisterReturnCode.REGISTER_FACE_NOT_DETECTED);
            }
            CustomerVO customerVO=customerService.getById(Integer.parseInt(id));
            List<CustomerVO> list=new ArrayList<CustomerVO>();
            list.add(customerVO);
            return new WebPageResult(list, 1);
        }
        else{
            List<CustomerVO> list=new ArrayList<CustomerVO>();
            return new WebPageResult(list,0);
        }
    }

    final Base64.Decoder decoder = Base64.getDecoder();
    public byte[] Base64ToByteArr(String b64)
    {
        String[] strArr=b64.split(",");
        return decoder.decode(strArr[1]);
    }
}

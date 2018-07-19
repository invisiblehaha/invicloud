package cn.vision.invicloud.web.controller.customer;
import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.ICustomerService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.pojo.vo.CustomerVO;
import cn.vision.invicloud.support.common.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Author: Hattori
 * @Date: 2018/7/7 2:24
 * @Description:
 */
@Controller
@RequestMapping(value = "/customer/customer")
@SessionAttributes(value = {"menus","user"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "/view")
    public String getListPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user) {
        return "/customer/customer";
    }

    @GetMapping(value = "/")
    @ResponseBody
    public Object listCustomer(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        BasePageDTO<CustomerVO> basePageDTO = customerService.listByPage2(pageInfo, search);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }

    @PostMapping(value="/edit")
    @ResponseBody
    public void edit(Customer customer){
        System.out.println(customer.getAge());
    }
}

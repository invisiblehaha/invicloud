package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.pojo.vo.OrderVO;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.IOrderService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/order/order")
@SessionAttributes(value = {"menus","user"})
public class OrderController {

    @Autowired
    private IOrderService OrderService;

    /**
     * GET &#x65e5;&#x8bb0;&#x8bb0;&#x5f55;
     * @return
     */
    @GetMapping(value = "/view")
    public String list(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user) {
        return "/order/order";
    }

    /**
     * GET
     * @return
     */
    @GetMapping(value = "/")
    @ResponseBody
    public Object listUser(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        BasePageDTO<OrderVO> basePageDTO = OrderService.list(null, pageInfo,search);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }
}

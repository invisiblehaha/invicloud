package cn.vision.invicloud.web.controller.product;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.pojo.vo.OrderVO;
import cn.vision.invicloud.support.service.IOrderService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/order/order")
public class OrderController {

    @Autowired
    private IOrderService OrderService;

    /**
     * GET &#x65e5;&#x8bb0;&#x8bb0;&#x5f55;
     * @return
     */
    @GetMapping(value = "/view")
    public String list(Model model) {
        return "/order/order";
    }

    /**
     * GET »’º«º«¬º
     * @return
     */
    @GetMapping(value = "/")
    @ResponseBody
    public Object listUser(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        BasePageDTO<OrderVO> basePageDTO = OrderService.list(null, pageInfo);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }
}

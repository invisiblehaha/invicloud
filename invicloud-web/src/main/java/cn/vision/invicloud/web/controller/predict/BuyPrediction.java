package cn.vision.invicloud.web.controller.predict;

import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/system/buy")
@SessionAttributes(value = {"menus","user"})
public class BuyPrediction {

    @GetMapping(value = "/predict")
    public String getChartPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user){
        return "/chart/buyAmountPrediction";
    }
}

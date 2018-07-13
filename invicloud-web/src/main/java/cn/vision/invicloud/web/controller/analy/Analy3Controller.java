package cn.vision.invicloud.web.controller.analy;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.pojo.vo.LikeVO;
import cn.vision.invicloud.support.service.IOrderAnalyService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = "/system/analy3")
public class Analy3Controller {
    @Autowired
    private IOrderAnalyService analyService;

    @GetMapping(value = "/view")
    public String list(Model model) {
        return "/system/analy3";
    }


    @PostMapping(value = "/")
    @ResponseBody
    public Object listLevels(PageInfo pageInfo){
        String file=System.getProperty("user.dir")+"/recommendationByCustomerCF.txt";

        BasePageDTO<LikeVO> basePageDTO=analyService.listLike(file,pageInfo);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }
}

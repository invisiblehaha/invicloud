package cn.vision.invicloud.web.controller.analy;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.pojo.vo.LevelVO;
import cn.vision.invicloud.support.service.IOrderAnalyService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/system/analy")
public class AnalyController {
    @Autowired
    private IOrderAnalyService analyService;

    @GetMapping(value = "/view")
    public String list(Model model) {
        return "/system/analy";
    }
    @PostMapping(value = "/generate")
    @ResponseBody
    public Object generateTxts(){
        analyService.toTxt();
        analyService.toTxt2();
        analyService.buyAmount();
        analyService.payAmount();
        analyService.rfm();
        return "redirect:/view";
    }
    @PostMapping(value = "/")
    @ResponseBody
    public Object listLevels(PageInfo pageInfo){
        String file=System.getProperty("user.dir")+"/consumptionLevel.txt";
        BasePageDTO<LevelVO> basePageDTO=analyService.listLevel(file,pageInfo);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }
}

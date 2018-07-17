package cn.vision.invicloud.web.controller.system;

import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.common.PageInfo;
import cn.vision.invicloud.support.entity.Log;
import cn.vision.invicloud.support.service.ILogService;
import cn.vision.invicloud.web.common.WebPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Hattori
 * @Date: 2018/7/7 2:23
 * @Description:
 */
@Controller
@RequestMapping(value = "/system/log")
public class SystemLogController {



    @Autowired
    private ILogService logService;

    /**
     * GET 日记记录
     * @return
     */
    @GetMapping(value = "/view")
    public String list(Model model) {
        return "/system/log";
    }

    /**
     * GET 日记记录
     * @return
     */
    @GetMapping(value = "/")
    @ResponseBody
    public Object listUser(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        BasePageDTO<Log> basePageDTO = logService.listByPage(pageInfo, search);
        return new WebPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }
}

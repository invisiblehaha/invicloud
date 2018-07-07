package cn.vision.invicloud.web.controller.system;

import cn.vision.invicloud.support.entity.Log;
import cn.vision.invicloud.support.service.ILogService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
     * GET 日记
     * @return
     */
    @GetMapping(value = "/view")
    public String getLogPage(Model model) {
        return "";
    }

    /**
     * GET 日记记录
     * @return
     */
    @GetMapping(value = "/")
    @ResponseBody
    public Object informationList(String pageNumber, String pageSize,@RequestParam(required = false, value = "search")String search) {
        int page_Num = Integer.parseInt(pageNumber);
        int page_Size = Integer.parseInt(pageSize);
        Page<Log> page = new Page<Log>(page_Num, page_Size);
        Page<Log> resultPage = logService.listByPage(page,search);
        //bootstrap-table要求服务器返回的json须包含：total，rows
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", resultPage.getTotal());
        map.put("rows", resultPage.getRecords());
        return map;
    }
}

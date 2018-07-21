package cn.vision.invicloud.web.controller.detect;
import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.pojo.vo.RoleMenuVO;
import cn.vision.invicloud.support.pojo.vo.UserVO;
import cn.vision.invicloud.support.service.ICustomerService;
import cn.vision.invicloud.support.entity.EmotionRecord;
import cn.vision.invicloud.web.common.WebPageResult;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.RegisterReturnCode;
import cn.vision.invicloud.web.common.utils.UpdateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cn.vision.invicloud.support.common.BasePageDTO;
import cn.vision.invicloud.support.pojo.vo.CustomerVO;
import cn.vision.invicloud.support.common.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import humanfaceAPI.*;
import cn.vision.invicloud.support.service.IEmotionRecordService;

import java.util.*;

@Controller
@RequestMapping(value = "/detect/detect")
@SessionAttributes(value = {"menus","user"})
public class DetectController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmotionRecordService emotionRecordService;

    @GetMapping(value = "/view")
    public String getListPage(@ModelAttribute("menus")List<RoleMenuVO> menus, @ModelAttribute("user") UserVO user) {
        return "/detect/detect";
    }

    @GetMapping(value = "/nothing")
    @ResponseBody Object doNothing()
    {
        List<CustomerVO> list=new ArrayList<CustomerVO>();
        return new WebPageResult(list,0);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public Object listCustomer(PageInfo pageInfo,@RequestParam(required = false, value = "imgString")String b64String, @RequestParam(required = false, value = "emotion")String cusEmotion) {
        String id=null;
        if(b64String!=null) {
 			Date GMTtime = new Date();
            Calendar cal = Calendar.getInstance();

            cal.setTime(GMTtime);
            cal.add(Calendar.HOUR,8);
            Date now = cal.getTime();
            System.out.println(b64String);
            byte[] buff = UpdateUtils.Base64ToByteArr(b64String);
            id = InterfaceOfAllAPIs.searchForUserId(buff, "FS_1");
 String detectToken = InterfaceOfAllAPIs.detect(buff);
            System.out.println(detectToken);
            EmotionRecord record = new EmotionRecord();
            record.setCustomerId(id);
            record.setEmotion(cusEmotion);
            record.setRecordTime(now);
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

            try {
                emotionRecordService.insertRecord(record);
            } catch (Exception e) {
                e.printStackTrace();
            }

            UpdateUtils.updatePic(buff,customerVO.getCustomerId().toString());
            return new WebPageResult(list, 1);
        }
        else{
            List<CustomerVO> list=new ArrayList<CustomerVO>();
            return new WebPageResult(list,0);
        }
    }

   
}
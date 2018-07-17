package cn.vision.invicloud.web.controller.system;

import cn.vision.invicloud.support.entity.Customer;
import cn.vision.invicloud.support.service.ICustomerService;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.enums.RegisterReturnCode;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import humanfaceAPI.*;
import java.util.Calendar;
import java.util.Date;


@Controller
@RequestMapping("register/register")
public class RegisterController {
/*
    protected Logger logger = LoggerFactory.getLogger(RegisterController.class);
    //register的日志

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private HttpServletRequest request;
*/

    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "/view")
    public String getRegisterPage(Model model)
    {
        return "/register/register";
    }


    final Base64.Decoder decoder = Base64.getDecoder();
    public byte[] Base64ToByteArr(String b64)
    {
        String[] strArr=b64.split(",");
        return decoder.decode(strArr[1]);
    }

    /**
     * POST 注册信息
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public Object register(@RequestParam("userName")String registerName,
                        @RequestParam("sex")Integer sex, //0==保密 1==男 2==女
                        @RequestParam("birthYear")Integer birthYear,
                        @RequestParam("phoneNumber")String phoneNumber,
                        @RequestParam("img")String imgString)
    {
        byte[] buff=Base64ToByteArr(imgString);
        String detectToken = InterfaceOfAllAPIs.detect(buff);

        if(InterfaceOfAllAPIs.searchForUserId(buff,"FS_1")!=Key.KEY_FOR_SEARCH_MATCHFAILED_MESSAGE)
        {
            return new WebResult(RegisterReturnCode.FACE_REGISTER_TWICE);
        }//如果是同一张人脸，则不允许重复注册

        Date GMTtime = new Date();
        Calendar cal = Calendar.getInstance();

        cal.setTime(GMTtime);
        cal.add(Calendar.HOUR,8);
        Date now = cal.getTime();

        Integer theCustomerId = customerService.getLastestPlusCustomerId()+1;//从数据库中获取最新的id + 1,即预备绑定给用户的id

        Customer customer = new Customer();
        customer.setCustomerId(theCustomerId);
        customer.setCustomerToken(detectToken);
        customer.setAge(cal.get(Calendar.YEAR)-birthYear);
        customer.setPicImg("picImg");
        customer.setNoble(1);
        customer.setRealName(registerName);
        customer.setRegeistTime(now);
        customer.setSex(sex);
        customer.setStatus(0);
        customer.setTelephone(phoneNumber);


        //识别不到人脸时
        if(detectToken == Key.KEY_FOR_DETECT_FAILED_MESSAGE)
        {
            return new WebResult(RegisterReturnCode.REGISTER_FACE_NOT_DETECTED);
        }

        try
        {
            customerService.insertCustomer(customer);

            String addLog = InterfaceOfAllAPIs.addOneFaceIntoFaceSet(detectToken,"FS_1");
            String bindLog = InterfaceOfAllAPIs.setUserIdForFaceToken(detectToken,Integer.toString(theCustomerId));
            System.out.println(addLog);
        }
        catch(Exception e)
        {
            System.out.println("添加到人脸集合出错!");
            return new WebResult(RegisterReturnCode.INFO_NOT_COMPLETE);
        }

       /* try
        {
            customerService.insertCustomer(customer);
           *//* System.out.println(insertResult);//调试用，如果系统运行正常请删除*//*
        }
        catch(Exception e)
        {
            System.out.println("数据库操作异常！");
            return new WebResult(RegisterReturnCode.ADDTO_DATABASE_FAILED);
    }*/


        return new WebResult(CommonReturnCode.SUCCESS);

    }



}

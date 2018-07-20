package cn.vision.invicloud.web.controller.system;

import cn.vision.invicloud.support.entity.User;
import cn.vision.invicloud.support.entity.UserRole;
import cn.vision.invicloud.support.service.IUserService;
import cn.vision.invicloud.support.service.IUserRoleService;
import cn.vision.invicloud.web.common.WebResult;
import cn.vision.invicloud.web.common.enums.CommonReturnCode;
import cn.vision.invicloud.web.common.enums.RegisterReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import humanfaceAPI.*;
import java.util.Calendar;
import java.util.Date;


@Controller
@RequestMapping("/register/UserRegister")
public class UserRegisterController {

/*
    @Autowired
    private ICustomerService customerService;
*/

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping(value = "/view")
    public String getRegisterPage(Model model) {
        return "/register/UserRegister";
    }


    final Base64.Decoder decoder = Base64.getDecoder();

    public byte[] Base64ToByteArr(String b64) {
        String[] strArr = b64.split(",");
        return decoder.decode(strArr[1]);
    }

    /**
     * POST 注册信息
     */
    @PostMapping(value = "/UserRegister")
    @ResponseBody
    public Object register(@RequestParam("loginName") String loginName,
                           @RequestParam("password") String password,
                           @RequestParam("userName") String userName,
                           @RequestParam("sex") Integer sex, //0==保密 1==男 2==女
                           @RequestParam("birthYear") Integer birthYear,
                           @RequestParam("role") Integer role,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("img") String imgString) {
        byte[] buff = Base64ToByteArr(imgString);
        String detectToken = InterfaceOfAllAPIs.detect(buff);

        //识别不到人脸时
        if (detectToken == Key.KEY_FOR_DETECT_FAILED_MESSAGE) {
            return new WebResult(RegisterReturnCode.REGISTER_FACE_NOT_DETECTED);
        }
        System.out.println(detectToken);
        String searchResult = InterfaceOfAllAPIs.searchForUserId(buff, "FS_0");
        if (searchResult != Key.KEY_FOR_SEARCH_MATCHFAILED_MESSAGE) {
            return new WebResult(RegisterReturnCode.FACE_REGISTER_TWICE);
        }//如果是同一张人脸，则不允许重复注册

        Date GMTtime = new Date();
        Calendar cal = Calendar.getInstance();

        cal.setTime(GMTtime);
        cal.add(Calendar.HOUR, 8);
        Date now = cal.getTime();


        String[] roleId = String.valueOf(role).split("^");//单元素string数组

        User user = new User();
        user.setLoginName(loginName);
        user.setLoginPassword(password);
        user.setUserName(userName);
        user.setSex(sex);
        user.setAge(cal.get(Calendar.YEAR) - birthYear);
        user.setPicImg("picImg");
        user.setStatus(1);
        user.setTelephone(phoneNumber);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setFaceToken(detectToken);

        UserRole userRole = new UserRole();
        userRole.setRoleId(role);
        userRole.setCreateTime(now);


        try {
            userService.insertUser(user, roleId);

            Integer theUserId = userService.getLastestPlusCustomerId();
            userRole.setUserId(theUserId);

            userRoleService.insert(userRole);

            InterfaceOfAllAPIs.addOneFaceIntoFaceSet(detectToken, "FS_0");
            InterfaceOfAllAPIs.setUserIdForFaceToken(detectToken, Integer.toString(theUserId));
            /*System.out.println(addLog);*/
        } catch (Exception e) {
            System.out.println("添加到人脸集合出错!");
            return new WebResult(RegisterReturnCode.INFO_NOT_COMPLETE);
        }


        return new WebResult(CommonReturnCode.SUCCESS);

    }


}

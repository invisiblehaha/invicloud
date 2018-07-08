package com.faceRec;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import humanfaceAPI.*;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String phone=request.getParameter("phone");
        String imgString=request.getParameter("img");
        byte[] buff=Base64ToByteArr(imgString);//byte64的图片转化到byte[]形式
        //System.out.println(username);

        try{


            String detectResult = InterfaceOfAllAPIs.detect(buff);
            /*String addResult = InterfaceOfAllAPIs.addOneFaceIntoFaceSet(detectResult,"FS_1");//add the face data into faceset "FS_1"
            String bindResult = InterfaceOfAllAPIs.setUserIdForFaceToken(detectResult,"person_5");//将userid与detect的facetoken绑定*/


           String searchResult = InterfaceOfAllAPIs.searchForUserId(buff,"FS_1");//return the best suit userid
            System.out.println(searchResult);

            String faceSetMessage = InterfaceOfAllAPIs.getDetailsOfFaceSet("FS_1");
            System.out.println(faceSetMessage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       /* User user=new User(username,password,phone,"123");
        DBHelper dbHelper=new DBHelper();
        try {
            dbHelper.test(user);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //dbHelper.add(user);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    final Base64.Decoder decoder = Base64.getDecoder();
    public byte[] Base64ToByteArr(String b64)
    {
        String[] strArr=b64.split(",");
        return decoder.decode(strArr[1]);
    }
}

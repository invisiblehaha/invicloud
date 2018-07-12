package cn.vision.invicloud.web.controller;

import cn.vision.invicloud.web.websocket.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Controller

public class WebSocketController {

    @Bean
    public MessageHandler messageHandler(){
        return new MessageHandler();
    }

    @RequestMapping("/websocket/loginPage")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/websocket/login";
    }


    @RequestMapping("/websocket/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username + "登录");
        HttpSession session = request.getSession(false);
        return "/websocket/send";

    }

    @RequestMapping("/websocket/broad")
    @ResponseBody
    public String broad() {
        messageHandler().sendMessageToAll(new TextMessage("发送一条小Broad"));
        System.out.println("群发成功");
        return "broad";
    }
}

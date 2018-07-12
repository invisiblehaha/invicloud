package cn.vision.invicloud.web.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions=new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //TODO:查詢未處理客戶
        System.out.println("成功建立websocket连接!");
        sessions.add(session);
        session.sendMessage(new TextMessage("你連上了 老哥"));
        System.out.println("当前线上用户数量:"+sessions.size());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //TODO:
        if (message instanceof TextMessage) {
            this.handleTextMessage(session, (TextMessage)message);
            System.out.println("服务器收到消息："+message);
            sendMessageToAll(new TextMessage("服务器群发：" +message.getPayload()));
        } else if (message instanceof BinaryMessage) {
            this.handleBinaryMessage(session, (BinaryMessage)message);
        } else {
            if (!(message instanceof PongMessage)) {
                throw new IllegalStateException("Unexpected WebSocket message type: " + message);
            }
            this.handlePongMessage(session, (PongMessage)message);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("有人掉了");
        sessions.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Someone logout");
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToAll(TextMessage message){
        for(WebSocketSession session:sessions){
            try{
                if(session.isOpen()){
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
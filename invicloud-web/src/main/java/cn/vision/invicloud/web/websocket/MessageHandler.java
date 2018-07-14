package cn.vision.invicloud.web.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions=new CopyOnWriteArrayList<>();
    private static final Set<String> users=new ConcurrentSkipListSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //TODO:查詢未處理客戶
        System.out.println("成功建立websocket连接!");
        sessions.add(session);
        for(String user:users)
            session.sendMessage(new TextMessage("1 "+user));

        System.out.println("当前线上用户数量:"+sessions.size());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //TODO:
        if (message instanceof TextMessage) {
            this.handleTextMessage(session, (TextMessage)message);
            System.out.println("服务器收到消息："+message);

            String msg=((TextMessage) message).getPayload();
            boolean flag=false;
            if (msg.charAt(0)=='1'){
                flag=users.contains(msg.substring(2));
                users.add(msg.substring(2));
            }
            else if(msg.charAt(0)=='0')users.remove(msg.substring(2));
            else throw new IllegalStateException("Unexpected Textmessage:message");

            if(!flag)sendMessageToAll(new TextMessage(""+message.getPayload()));
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
        System.out.println("有人退群了");
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
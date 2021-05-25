package controller.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Message;
import service.MsgService;
import util.ConnPool;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.*;

@ServerEndpoint(value="/chatWebsocket/{myId}")
public class ChatWebSocket {
    private Session session;//与某个客户端的连接会话，需要通过它来给客户端发送数据
    @OnOpen
    public void onOpen(Session session, @PathParam("myId")String myId){
        ConnPool.addUser(myId,session);
    }
    @OnClose
    public void onClose(Session session){
        ConnPool.removeUser(session);
    }
    @OnMessage
    public void onMessage(String message,Session session) throws IOException, SQLException, IllegalAccessException {
        Message msg=new ObjectMapper().readValue(message,Message.class);//获得反序列化的message
        Session friendSession=ConnPool.getConnByUser(msg.getToId());
        if(friendSession!=null){//如果好友在线，将message转发给他
            friendSession.getBasicRemote().sendText(message);
        }
        MsgService.sendMsg(msg);//将message存到数据库
    }
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}

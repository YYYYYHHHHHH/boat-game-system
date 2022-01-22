package com.example.demo.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.AuthUserDao;
import com.example.demo.entity.AuthUser;
import com.example.demo.entity.GroupChat.SocketData;
import com.example.demo.entity.Room;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/imserver/room/{userId}")
@Component
public class RoomWebSocketServer{
    static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,RoomWebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private static AuthUserDao authUserDao;
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";
    private static boolean add;

    @Autowired
    public void setAuthUserDao (AuthUserDao authUserDao){
        RoomWebSocketServer.authUserDao= authUserDao;
    }

    // 连接成功的调用方法
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }
    }

    // 连接关闭调用的方法
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId",this.userId);
                String toUserId=jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                }else{
                    log.error("请求的userId:"+toUserId+"不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送用户Id
     */
    public boolean sendUserId(Room room){
            if(webSocketMap.containsKey(room.getRoomHolderId().toString())){
                String holderId = room.getRoomHolderId().toString();
                String visitorId = room.getRoomVisitorId().toString();
                webSocketMap.get(holderId).sendMessage(visitorId);
            }else {
                return false;
            }
            return true;
    }

    public void sendUserId(String msg,Integer msgTo) throws IOException {
            if (webSocketMap.containsKey(msgTo)) {
                webSocketMap.get(msgTo).sendMessage(msg);
            }
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        }catch (IOException e){

        }
    }

    public static synchronized void addOnlineCount() {
        RoomWebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        RoomWebSocketServer.onlineCount--;
    }
}

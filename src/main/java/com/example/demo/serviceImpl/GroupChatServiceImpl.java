package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.component.WebSocketServer;
import com.example.demo.entity.AuthUser;
import com.example.demo.entity.GroupChat.Chat;
import com.example.demo.entity.GroupChat.ChatUserInfo;
import com.example.demo.entity.GroupChat.SocketData;
import com.example.demo.service.GroupChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class GroupChatServiceImpl implements GroupChatService {
    @Autowired
    private WebSocketServer wServer;
    @Autowired
    private AuthUserServiceImpl authUserServiceImpl;
    @Resource
    private RedisTemplate<String, Chat>  redisTemplate;

    // private List<Chat> chats = new ArrayList<>();

    private List<AuthUser> lUsers = null;

    @Override
    public List<ChatUserInfo> getChatUsers() {
        lUsers = authUserServiceImpl.getUsers();
        List<Integer> userIds = wServer.getUserIdsOnLine();
        List<ChatUserInfo> chatUserInfos = new ArrayList<ChatUserInfo>();

        lUsers.forEach(user -> {
            boolean isOnLine = userIds.contains(user.getId());
            chatUserInfos.add(new ChatUserInfo(user, isOnLine));
        });

        return chatUserInfos;
    }

    @Override
    public List<Chat> getChatByLimit(int start, int num) {
        List<Chat> chats = (List<Chat>) redisTemplate.opsForList().range("chats", start, start + num);
        return chats;
    }

    @Override
    public void sendChat(int userId, String mes) {
        AuthUser user = lUsers.get(lUsers.indexOf(new AuthUser(userId)));
        Chat chat = new Chat(user, mes, new Date(System.currentTimeMillis()));
        redisTemplate.opsForList().leftPush("chats", chat);

        // 群发消息
        returnChat(new SocketData(3, chat));
    }

    @Override
    public void returnChat(SocketData data) {
        wServer.sendInfoAllOnLine(JSONObject.toJSONString(data));
    }
    
}

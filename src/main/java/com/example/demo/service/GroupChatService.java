package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.GroupChat.Chat;
import com.example.demo.entity.GroupChat.ChatUserInfo;
import com.example.demo.entity.GroupChat.SocketData;

public interface GroupChatService {
    /* 获取聊天室人员信息 */
    List<ChatUserInfo> getChatUsers();
    /* 获取聊天记录 */
    List<Chat> getChatByLimit(int start, int num);
    /* 客户端发送消息 */
    void sendChat(int userId, String mes);
    /* 服务端返回消息 */
    void returnChat(SocketData chat);
    
}

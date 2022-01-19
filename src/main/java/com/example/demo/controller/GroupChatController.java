package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.GroupChat.Chat;
import com.example.demo.entity.GroupChat.ChatUserInfo;
import com.example.demo.entity.GroupChat.SendChat;
import com.example.demo.serviceImpl.GroupChatServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group-chat")

public class GroupChatController {
    static Logger log = LoggerFactory.getLogger(GroupChatController.class);
    @Autowired
    private GroupChatServiceImpl groupChatServiceImpl;

    @GetMapping("/users")
    List<ChatUserInfo> getChatUsers() {
        return groupChatServiceImpl.getChatUsers();
    }

    @GetMapping("/his")
    List<Chat> getChatByLimit(@RequestParam("start") int start, @RequestParam("num") int num) {
        return groupChatServiceImpl.getChatByLimit(start, num);
    }
    
    @PostMapping(value = "/send")
    void sendChat(@RequestBody SendChat sendChat) {
        log.info(sendChat.toString());
        groupChatServiceImpl.sendChat(sendChat.getUserId(), sendChat.getCont());
    }
}

package com.example.demo.entity.GroupChat;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.entity.AuthUser;

public class Chat implements Serializable{
    private Integer id;

    private String nickname;

    private String avatar;

    private Date chatDateTime;
    
    private String chatCont;

    public Chat(AuthUser user, String chatCont, Date chatDateTime) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.chatCont = chatCont;
        this.chatDateTime = chatDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getChatDateTime() {
        return chatDateTime;
    }

    public void setChatDateTime(Date chatDateTime) {
        this.chatDateTime = chatDateTime;
    }

    public String getChatCont() {
        return chatCont;
    }

    public void setChatCont(String chatCont) {
        this.chatCont = chatCont;
    }

    
}

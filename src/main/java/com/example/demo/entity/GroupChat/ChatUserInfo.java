package com.example.demo.entity.GroupChat;

import com.example.demo.entity.AuthUser;

public class ChatUserInfo extends AuthUser {
    private boolean isOnLine;

    public boolean isOnLine() {
        return isOnLine;
    }

    public void setOnLine(boolean isOnLine) {
        this.isOnLine = isOnLine;
    }
    
    public ChatUserInfo(AuthUser user) {
        super(user);
    }

    public ChatUserInfo(AuthUser user, boolean isOnLine) {
        super(user);
        this.isOnLine = isOnLine;
    }
}

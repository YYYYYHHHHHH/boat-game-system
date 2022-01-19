package com.example.demo.entity.GroupChat;

public class SendChat {
    private int userId;

    private String cont;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    @Override
    public String toString() {
        return "SendChat [cont=" + cont + ", userId=" + userId + "]";
    }
    
    
}

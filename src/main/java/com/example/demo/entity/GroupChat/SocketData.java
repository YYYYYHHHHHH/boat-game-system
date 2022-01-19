package com.example.demo.entity.GroupChat;

public class SocketData {
    // 1 推送xxx登录了 2 推送xxx退出了 3 推送xxx的消息
    private int type;
    private Object data;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public SocketData(int type, Object data) {
        this.type = type;
        this.data = data;
    }    
}

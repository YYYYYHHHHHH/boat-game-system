package com.example.demo.entity;

import java.io.Serializable;

public class Room implements Serializable {

    /*房间ID*/
    private String roomId;
    /*房主Id*/
    private Integer roomHolderId;
    /*访客Id*/
    private Integer roomVisitorId;

    public Room(){

    }
    public Room(String roomId, Integer roomHolderId, Integer roomVisiterId){
        this.roomId = roomId;
        this.roomHolderId = roomHolderId;
        this.roomVisitorId = roomVisiterId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Integer getRoomHolderId() {
        return roomHolderId;
    }

    public Integer getRoomVisitorId() {
        return roomVisitorId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setRoomHolderId(Integer roomHolderId) {
        this.roomHolderId = roomHolderId;
    }

    public void setRoomVisitorId(Integer roomVisitorId) {
        this.roomVisitorId = roomVisitorId;
    }

}

package com.example.demo.service;

import com.example.demo.entity.Room;

import java.util.Map;

public interface RoomService {
    /*创建房间*/
    boolean createRoom(String roomId,Integer holderId);
    /*查找房间*/
    Map<String,Room> searchRoom(String roomId);
    /*加入房间*/
    String addInRoom(String roomId,Integer visitorId);
}

package com.example.demo.controller;

import com.example.demo.serviceImpl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/api/room")
public class DoubleUserOnlineController {
    @Autowired
    private RoomServiceImpl roomServiceImpl;

    /*创建房间*/
    @GetMapping("create")
    boolean createRoom(@RequestParam("roomId") String roomId, @RequestParam("holderId") int holderId){
        return roomServiceImpl.createRoom(roomId,holderId);
    }
    /*查询房间*/
    @GetMapping("search")
    Map searchRoom(@RequestParam("roomId") String roomId){
        return roomServiceImpl.searchRoom(roomId);
    }
    /*加入房间*/
    @GetMapping("addIn")
    String addInRoom(@RequestParam("roomId") String roomId,@RequestParam("visitorId") Integer visitorId){
        return roomServiceImpl.addInRoom(roomId,visitorId);
    }
}

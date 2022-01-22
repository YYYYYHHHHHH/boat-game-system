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

    /*创建房间
    * @param String roomId
    * @param int holderId
    * @return boolean result 成功/失败
    * */
    @GetMapping("create")
    boolean createRoom(@RequestParam("roomId") String roomId, @RequestParam("holderId") int holderId){
        return roomServiceImpl.createRoom(roomId,holderId);
    }
    /*查询房间
    * @param String roomId
    * @return Map<roomId,房间信息>
    * */
    @GetMapping("search")
    Map searchRoom(@RequestParam("roomId") String roomId){
        return roomServiceImpl.searchRoom(roomId);
    }
    /*加入房间
    * @param String roomId
    * @param int visitorId
    * @return String 加入房间是否成功的信息
    * */
    @GetMapping("addIn")
    String addInRoom(@RequestParam("roomId") String roomId,@RequestParam("visitorId") Integer visitorId){
        return roomServiceImpl.addInRoom(roomId,visitorId);
    }

    /*退出房间*/
    @GetMapping("exit")
    boolean exitRoom(@RequestParam("roomId") String roomId,@RequestParam("userId") Integer userId){
        return roomServiceImpl.exitRoom(roomId,userId);
    }

}

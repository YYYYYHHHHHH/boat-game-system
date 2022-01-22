package com.example.demo.serviceImpl;

import com.example.demo.component.WebSocketServer;
import com.example.demo.entity.GroupChat.Chat;
import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private WebSocketServer wServer;
    @Resource
    private RedisTemplate<String, Room> redisTemplate;
    /*数据集*/
    private Map roomMap = new HashMap<String,Room>();

    /*创建房间*/
    @Override
    public boolean createRoom(String roomId, Integer holderId){
        boolean result = true;
        /*生成房间*/
        Room room = new Room(roomId,holderId,null);
        /*房间存入数据集*/
        roomMap.put(roomId,room);
        /*数据集存入redis*/
        redisTemplate.opsForValue().multiSet(roomMap);

        return true;
    }

    /*查找房间*/
    public Map<String,Room> searchRoom(String roomId){
        /*map返回值*/
        Map<String,Room> resultMap = new HashMap<>();
        /*List存储ID*/
        List<String> roomIdList = new ArrayList<>();
        roomIdList.add(roomId);
        List<Room> roomList = null;
        /*进行房间查询操作*/
        try{
            /*房间不存在则报错*/
            roomList = redisTemplate.opsForValue().multiGet(roomIdList);
        }catch (Exception e){
        }

        if(roomList != null && !roomList.isEmpty()){
            /*房间信息存入map*/
            resultMap.put(roomId,roomList.get(0));
        }else{
            /*房间信息不存在返回NULL*/
            resultMap.put(roomId,null);
        }

        return resultMap;
    }

    /*加入房间*/
    @Override
    public String addInRoom(String roomId,Integer visitorId){

        /*房间信息查询*/
        List<String> roomIdList = new ArrayList<>();
        roomIdList.add(roomId);
        List<Room> roomList = null;
        /*进行房间查询操作*/
        try{
            /*房间不存在则报错*/
            roomList = redisTemplate.opsForValue().multiGet(roomIdList);
        }catch (Exception e){
            return "房间不存在";
        }
        /*房间是否满员*/
        if(roomList.get(0).getRoomVisitorId() != null){
            /*满员则返回null*/
            return "房间满员";
        }else{
            /*未满员则加入房间*/
            roomList.get(0).setRoomVisitorId(visitorId);
            /*房间存入数据集*/
            roomMap.put(roomId,roomList.get(0));
            /*数据集存入redis*/
            redisTemplate.opsForValue().multiSet(roomMap);
        }
        /*调用websocket给房主客户端送信访客Id*/
        return  wServer.sendUserId(visitorId);

    }
}

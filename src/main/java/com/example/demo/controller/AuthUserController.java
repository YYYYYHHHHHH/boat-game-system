package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.component.WebSocketServer;
import com.example.demo.entity.AuthUser;
import com.example.demo.entity.GroupChat.SocketData;
import com.example.demo.exception.UserException;
import com.example.demo.serviceImpl.AuthUserServiceImpl;
import com.example.demo.serviceImpl.GroupChatServiceImpl;
import com.example.demo.status.UserResultCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/api/user")

public class AuthUserController {
    @Autowired
    private AuthUserServiceImpl authUserService;
    @Autowired
    private WebSocketServer webSocketServer;

    @PostMapping("/login")
    AuthUser login(@RequestBody AuthUser user) throws UserException {
        AuthUser res = authUserService.login(user);
        if (res == null)
            throw new UserException(UserResultCode.LOGIN_FAILD);

        webSocketServer.sendInfoAllOnLine(JSONObject.toJSONString(new SocketData(1, res)));
        return res;
    }

    @GetMapping("/users")
    List<AuthUser> getUsers() {
        return authUserService.getUsers();
    }

    @PostMapping("/users")
    int addUser(@RequestBody AuthUser user) {
        return authUserService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    int deleteUser(@PathVariable("id") int id) {
        return authUserService.deleteUser(id);
    }

    @PutMapping("/users")
    int editUser(@RequestBody AuthUser user) {
        return authUserService.editUser(user);
    }

    // 注入配置中图片保存路径
    @Value("${user.filepath}")
    private String filePath;
    @Value("${user.ip}")
    private String ip;
    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    String uploadAvatar(@RequestPart("avatar") MultipartFile multipartFile, @RequestParam("id") int id)
            throws IllegalStateException, IOException {
        UUID uuid = UUID.randomUUID();
        String originalFileName = multipartFile.getOriginalFilename();
        String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));

        File file = new File(filePath + uuid + fileSuffix);
        multipartFile.transferTo(file);

        String avatarUrl = "http://" + ip + ":" + port + "/api/images/" + uuid + fileSuffix;
        AuthUser user = new AuthUser();
        user.setAvatar(avatarUrl);
        user.setId(id);

        authUserService.editUser(user);
        return avatarUrl;
    }
}

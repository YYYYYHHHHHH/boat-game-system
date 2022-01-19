package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AuthUser;

public interface AuthUserService {
    // 获取用户列表
    List<AuthUser> getUsers();

    // 添加用户
    int addUser(AuthUser user);

    // 删除用户
    int deleteUser(int id);

    // 修改用户
    int editUser(AuthUser user);

    // 用户登录
    AuthUser login(AuthUser user);

}

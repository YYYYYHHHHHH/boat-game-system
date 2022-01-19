package com.example.demo.serviceImpl;

import java.util.List;

import com.example.demo.dao.AuthUserDao;
import com.example.demo.entity.AuthUser;
import com.example.demo.service.AuthUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserDao dao;

    @Override
    public List<AuthUser> getUsers() {
        return dao.queryAll(new AuthUser());
    }

    @Override
    public int addUser(AuthUser user) {
        return dao.insert(user);
    }

    @Override
    public int deleteUser(int id) {
        return dao.deleteById(id);
    }

    @Override
    public int editUser(AuthUser user) {
        return dao.update(user);
    }

    @Override
    public AuthUser login(AuthUser user) {
        List<AuthUser> list = dao.queryAll(user);

        return list.size() == 0 ? null : list.get(0);
    }
}

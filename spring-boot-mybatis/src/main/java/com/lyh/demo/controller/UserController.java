package com.lyh.demo.controller;

import com.lyh.demo.mapper.UserMapper;
import com.lyh.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/getUsers")
    public String getUsers(){
        List<UserEntity> users=userMapper.getAll();
        return users.toString();
    }
    @RequestMapping("/setUsers")
    public void setUsers(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId(3);
        userEntity.setUserName("user3");
        userEntity.setPassword("123456");
        userMapper.insert(userEntity);
    }
}

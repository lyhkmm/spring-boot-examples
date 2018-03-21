package com.lyh.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.lyh.demo.dao.UserDao;
import com.lyh.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloWorldController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/helloWorld")
    public String helloWorld(){
        return "HelloWorld";
    }

    @RequestMapping("/save")
    public String testSaveUser() throws Exception {
        UserEntity user=new UserEntity();
        user.setId(520L);
        user.setUserName("小林");
        user.setPassword("111111");
        userDao.saveUser(user);
        return "保存成功";
    }

    @RequestMapping("/get")
    public String findUserByUserName(){
        UserEntity user= userDao.findUserByUserName("小林");
        System.out.println("user :"+user.toString());
        return "user :"+user.toString();
    }

    @RequestMapping("/update")
    public String updateUser(){
        UserEntity user=new UserEntity();
        user.setId(520L);
        user.setUserName("大林");
        user.setPassword("123456");
        userDao.updateUser(user);
        return "更新成功";
    }

    @RequestMapping("/delete")
    public String  deleteUserById(){
        userDao.deleteUserById(520L);
        return "删除成功";
    }
}

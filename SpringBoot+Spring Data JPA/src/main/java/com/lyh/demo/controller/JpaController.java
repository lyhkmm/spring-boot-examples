package com.lyh.demo.controller;

import com.lyh.demo.entity.GameRank;
import com.lyh.demo.entity.User;
import com.lyh.demo.entity.enums.Sex;
import com.lyh.demo.repository.GameScoreRepository;
import com.lyh.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
public class JpaController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameScoreRepository gameScoreRepository;

    @RequestMapping("/findAll")
    public void findAll(){
        //使用默认方法findAll()获取所用数据
        List<User> userList = userRepository.findAll();
        if(userList !=null){
            for (User user : userList){
                System.out.println(user.toString());
            }
        }
/*
        User user =userRepository.findOne(1L);
        user =userRepository.findOne(1L);
        这些默认方法可根据方法名可以看出方法的作用，这里就不一一说明了
        userRepository.save(user);
        userRepository.delete(user);
        userRepository.deleteAll();
        userRepository.count();
        userRepository.exists(1L);*/
    }

    @RequestMapping("/myDefine")
    public void myDefine(){
        String userName="小明";
        String password="123456";

        List<User> userList =userRepository.findByUserName(userName);

        User user1 =userRepository.findByUserNameAndPassword(userName,password);

        User user2 =userRepository.findTopByOrderByAgeDesc();
    }
    @RequestMapping("/page")
    public void page() {
        //分页的页码，从0开始
        int page=0;
        //每页有多少行数据，2就表示两条
        int size=2;
        String userName="小明";
        //排序规则
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        //添加页面、行数和排序规则
        Pageable pageable=new PageRequest(page,size,sort);
        //根据分页规则查找所有数据
        Page<User> userPage1=userRepository.findAll(pageable);
        if(userPage1!=null){
            System.out.println("findAll：");
            for (User user :userPage1){
                System.out.println(user.toString());
            }
        }
        //根据分页规则查找指定用户的数据
        Page<User> userPage2=userRepository.findByUserName(userName,pageable);
        if(userPage2!=null){
            System.out.println("findByUserName：");
            for (User user :userPage2){
                System.out.println(user.toString());
            }
        }
    }

    @RequestMapping("/limit")
    public void limit(){
        int page=0;
        int size=3;
        int age=20;
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(page,size);
        //根据排序规则和年龄获取前2行
        List<User> userList1 =userRepository.findFirst2ByAge(age,sort);
        if(userList1 !=null){
            System.out.println("findFirst2ByAge：");
            for (User user : userList1){
                System.out.println(user.toString());
            }
        }
        //分页年龄获取前三行
        List<User> userList2 =userRepository.findTop3ByAge(age,pageable);
        if(userList2 !=null){
            System.out.println("findTop3ByAge：");
            for (User user : userList2){
                System.out.println(user.toString());
            }
        }
        //这里的query官方文档没有介绍，测试发现和find功能一样
        Page<User> userPage1=userRepository.queryFirst3ByAge(age,pageable);
        if(userPage1!=null){
            System.out.println("queryFirst3ByAge：");
            for (User user :userPage1){
                System.out.println(user.toString());
            }
        }
    }

    @RequestMapping("/update")
    public void update(){
        //userRepository.updateUserNameById(1,"大明");
        User user=new User();
        user.setSex(Sex.女);
        userRepository.save(user);
    }

    @RequestMapping("/findRankByName")
    public void findRankByName(){
        List<GameRank> gameRankList=gameScoreRepository.findRankByName("2048小游戏");
        for(GameRank gameRank:gameRankList){
            System.out.println("用户名："+gameRank.getName()+"  游戏名："+gameRank.getUserName()+"  得分"+gameRank.getScore());
        }
    }
}

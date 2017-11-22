package com.lyh.demo.controller;

import com.lyh.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
public class HelloWorldController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/setRedis")
    public String setRedis(){

        ValueOperations<String,String> valueOperationsStrRedis=stringRedisTemplate.opsForValue();
        ValueOperations<String,Object> valueOperationsRedis=redisTemplate.opsForValue();
        valueOperationsStrRedis.set("info","你好！");
        User user=new User(System.currentTimeMillis(),"lyh","123456");
        valueOperationsRedis.set("user",user);
        return "success";
    }
    @RequestMapping("/getRedis")
    public String getRedis(){
        ValueOperations<String,String> valueOperationsStrRedis=stringRedisTemplate.opsForValue();
        ValueOperations<String,Object> valueOperationsRedis=redisTemplate.opsForValue();
        String info=valueOperationsStrRedis.get("info");
        User user=(User) valueOperationsRedis.get("user");
        return info+user.toString();
    }
}

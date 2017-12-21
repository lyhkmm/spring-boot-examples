package com.lyh.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
public class HelloWorldController {
    @RequestMapping("/helloWorld")
    public String helloWorld(){
        return "HelloWorld";
    }
}

package com.leon.controller;


import com.leon.error.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String say(){
        return "hello, spring boot";
    }


    @RequestMapping("/show")
    public String testerror() throws Exception{
        throw new Exception("发生错误");
    }


    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

}

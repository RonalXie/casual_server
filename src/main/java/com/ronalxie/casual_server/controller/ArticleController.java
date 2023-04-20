package com.ronalxie.casual_server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }




}

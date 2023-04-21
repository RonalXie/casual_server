package com.ronalxie.casual_server.controller;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.dto.ArticleDto;
import com.ronalxie.casual_server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestBody ArticleDto articleDto){
        articleService.save(articleDto);
        return "hello world";
    }

}

package com.ronalxie.casual_server.controller;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.PageBean;
import com.ronalxie.casual_server.entity.RespBean;
import com.ronalxie.casual_server.entity.TreeMenuDo;
import com.ronalxie.casual_server.entity.dto.ArticleDto;
import com.ronalxie.casual_server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public RespBean save(@RequestBody ArticleDto articleDto){
        articleService.save(articleDto);
        return RespBean.success("新增成功!");
    }

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public RespBean selectPage(@RequestParam("pageNum") String pageNum,@RequestParam("pageSize") String pageSize){
        PageBean<ArticleDto> articleDoPageBean = articleService.selectPage(pageNum,pageSize);
        return RespBean.error("查询成功!",articleDoPageBean);
    }

}

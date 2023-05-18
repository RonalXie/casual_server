package com.ronalxie.casual_server.controller;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.PageBean;
import com.ronalxie.casual_server.entity.PageParam;
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

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public RespBean selectPage(PageParam pageParam,@RequestBody(required = false) ArticleDto articleDto) throws InterruptedException {


        PageBean<ArticleDto> articleDoPageBean = articleService.selectPage(pageParam,articleDto);
        return RespBean.success("查询成功!",articleDoPageBean);
    }

    @RequestMapping(value = "/select/{sid}",method = RequestMethod.POST)
    public RespBean selectArticle(@PathVariable("sid") String sid){
        return RespBean.success("文章信息",articleService.selectBySid(Long.parseLong(sid)));
    }

    @RequestMapping(value = "/delete/{sid}",method = RequestMethod.POST)
    public RespBean deleteArticle(@PathVariable String sid){
        articleService.deleteBySid(Long.parseLong(sid));
        return RespBean.success("删除成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RespBean updateArticle(@RequestBody ArticleDto articleDto){
        articleService.updateBySid(articleDto);
        return RespBean.success("更新成功");
    }

    @RequestMapping(value = "/hot",method = RequestMethod.POST)
    public RespBean selectHot(){
        List<ArticleDo> list=articleService.selectHot();
        return RespBean.success("热门文章",list);
    }
}

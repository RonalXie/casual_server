package com.ronalxie.casual_server.controller;

import com.ronalxie.casual_server.entity.PageBean;
import com.ronalxie.casual_server.entity.PageParam;
import com.ronalxie.casual_server.entity.RespBean;
import com.ronalxie.casual_server.entity.TagDo;
import com.ronalxie.casual_server.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(@RequestBody TagDo tagDo){
        tagService.save(tagDo);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public RespBean listTags(){
        return RespBean.success("标签列表",tagService.listTags());
    }


    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public RespBean select(PageParam pageParam,@RequestBody(required = false) TagDo tagDo){
        PageBean<TagDo> pageBean=tagService.selectPage(pageParam,tagDo);
        return RespBean.success("标签列表",pageBean);
    }
}

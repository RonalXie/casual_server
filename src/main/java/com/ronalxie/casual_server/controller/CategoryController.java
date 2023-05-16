package com.ronalxie.casual_server.controller;

import com.ronalxie.casual_server.entity.CategoryDo;
import com.ronalxie.casual_server.entity.RespBean;
import com.ronalxie.casual_server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public RespBean save(@RequestBody CategoryDo categoryDo){
        categoryService.insertCategory(categoryDo);
        return RespBean.success("保存成功");
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public RespBean searchList(){
        List<CategoryDo> categoryDos = categoryService.listCategoies();
        return RespBean.success("分类列表",categoryDos);
    }

}

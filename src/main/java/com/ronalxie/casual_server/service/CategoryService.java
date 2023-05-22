package com.ronalxie.casual_server.service;

import com.ronalxie.casual_server.entity.CategoryDo;
import com.ronalxie.casual_server.entity.PageBean;
import com.ronalxie.casual_server.entity.PageParam;

import java.util.List;

public interface CategoryService {


    public void insertCategory(CategoryDo categoryDo);

    public List<CategoryDo> listCategoies();

    PageBean<CategoryDo> selectPage(PageParam pageParam, CategoryDo categoryDo);
}

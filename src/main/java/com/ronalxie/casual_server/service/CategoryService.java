package com.ronalxie.casual_server.service;

import com.ronalxie.casual_server.entity.CategoryDo;

import java.util.List;

public interface CategoryService {


    public void insertCategory(CategoryDo categoryDo);

    public List<CategoryDo> listCategoies();
}

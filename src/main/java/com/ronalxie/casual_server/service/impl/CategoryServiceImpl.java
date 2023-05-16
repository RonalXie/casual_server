package com.ronalxie.casual_server.service.impl;

import com.ronalxie.casual_server.entity.CategoryDo;
import com.ronalxie.casual_server.mapper.CategoryDoMapper;
import com.ronalxie.casual_server.service.CategoryService;
import com.ronalxie.casual_server.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDoMapper categoryDoMapper;
    @Override
    public void insertCategory(CategoryDo categoryDo) {
        categoryDo.setSid(IDUtils.nextId());
        categoryDo.setCreateTime(new Date());
        categoryDoMapper.insert(categoryDo);
    }

    @Override
    public List<CategoryDo> listCategoies() {
        return categoryDoMapper.listCategories();
    }
}

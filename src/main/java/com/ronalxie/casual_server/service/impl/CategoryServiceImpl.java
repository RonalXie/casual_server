package com.ronalxie.casual_server.service.impl;

import com.ronalxie.casual_server.entity.CategoryDo;
import com.ronalxie.casual_server.entity.PageBean;
import com.ronalxie.casual_server.entity.PageParam;
import com.ronalxie.casual_server.entity.TagDo;
import com.ronalxie.casual_server.mapper.CategoryDoMapper;
import com.ronalxie.casual_server.service.CategoryService;
import com.ronalxie.casual_server.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Override
    public PageBean<CategoryDo> selectPage(PageParam pageParam, CategoryDo categoryDo) {
        int pageNum = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        int start=(pageNum-1)*pageSize;
        if (ObjectUtils.isEmpty(categoryDo)){
            categoryDo=new CategoryDo();
        }
        List<CategoryDo> categoryDos = categoryDoMapper.selectPage(start, pageSize, categoryDo);
        int total = categoryDoMapper.selectTotal(categoryDo);
        return new PageBean<CategoryDo>(pageSize,pageNum,total,categoryDos);
    }
}

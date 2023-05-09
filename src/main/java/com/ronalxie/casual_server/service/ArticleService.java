package com.ronalxie.casual_server.service;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.PageBean;
import com.ronalxie.casual_server.entity.TreeMenuDo;
import com.ronalxie.casual_server.entity.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    public void save(ArticleDto articleDto);

    public PageBean<ArticleDto> selectPage(String pageNum,String pageSize);

}

package com.ronalxie.casual_server.service;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.PageBean;
import com.ronalxie.casual_server.entity.PageParam;
import com.ronalxie.casual_server.entity.TreeMenuDo;
import com.ronalxie.casual_server.entity.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    public void save(ArticleDto articleDto);

    public PageBean<ArticleDto> selectPage(PageParam pageParam, ArticleDto articleDto);

    public ArticleDto selectBySid(long sid);

    public void updateBySid(ArticleDto articleDto);

    public void deleteBySid(long sid);

    List<ArticleDo> selectHot();
}

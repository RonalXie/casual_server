package com.ronalxie.casual_server.service.impl;

import com.ronalxie.casual_server.entity.*;
import com.ronalxie.casual_server.entity.dto.ArticleDto;
import com.ronalxie.casual_server.mapper.ArticleDoMapper;
import com.ronalxie.casual_server.mapper.TagDoMapper;
import com.ronalxie.casual_server.service.ArticleService;
import com.ronalxie.casual_server.util.IDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDoMapper articleDoMapper;

    @Autowired
    private TagDoMapper tagDoMapper;

    /**
     * 新增文章
     * @param articleDto
     */
    public void save(ArticleDto articleDto){
        List<Long> tags = articleDto.getTags();
        Long articleSid= IDUtils.nextId();
        for (Long tagSid:tags) {
            ArticleTagDo articleTagDo=new ArticleTagDo();
            articleTagDo.setArticle_sid(articleSid);
            articleTagDo.setTag_sid(tagSid);
            articleTagDo.setSid(IDUtils.nextId());
            articleDoMapper.insertArticleTag(articleTagDo);
        }
        ArticleDo articleDo=new ArticleDo();
        BeanUtils.copyProperties(articleDto,articleDo);
        articleDo.setSid(articleSid);
        articleDo.setCreateTime(new Date());
        articleDoMapper.insert(articleDo);

    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<ArticleDto> selectPage(String pageNum,String pageSize) {
        int pageNumInt = Integer.parseInt(pageNum);
        int pageSizeInt = Integer.parseInt(pageSize);
        int start=(pageNumInt-1)*pageSizeInt;
        List<ArticleDo> articleDos = articleDoMapper.selectPage(start, pageSizeInt);
        List<ArticleDto> articleDtoList=new ArrayList<>();
        for (ArticleDo article:
             articleDos) {
            List<Long> tagSids = articleDoMapper.selectTagSids(article.getSid());
            List<TagDo> tagDos = tagDoMapper.selectBySids(tagSids);
            ArticleDto articleDto=new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setTagDos(tagDos);
            articleDtoList.add(articleDto);
        }
        int total=articleDoMapper.selectTotal();
        return new PageBean<>(pageNumInt,pageSizeInt,total,articleDtoList);
    }


}

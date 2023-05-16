package com.ronalxie.casual_server.service.impl;

import com.ronalxie.casual_server.entity.*;
import com.ronalxie.casual_server.entity.dto.ArticleDto;
import com.ronalxie.casual_server.mapper.ArticleDoMapper;
import com.ronalxie.casual_server.mapper.CategoryDoMapper;
import com.ronalxie.casual_server.mapper.TagDoMapper;
import com.ronalxie.casual_server.service.ArticleService;
import com.ronalxie.casual_server.util.IDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDoMapper articleDoMapper;

    @Autowired
    private TagDoMapper tagDoMapper;

    @Autowired
    private CategoryDoMapper categoryDoMapper;

    /**
     * 新增文章
     * @param articleDto
     */
    @Transactional
    public void save(ArticleDto articleDto){
        List<Long> tags = articleDto.getTagSids();
        Long articleSid= IDUtils.nextId();
        //保存关联分类
        ArticleCategoryDo articleCategoryDo=new ArticleCategoryDo();
        articleCategoryDo.setArticleSid(articleSid);
        articleCategoryDo.setCategorySid(articleDto.getCategorySid());
        articleCategoryDo.setSid(IDUtils.nextId());
        articleDoMapper.insertArticleCategory(articleCategoryDo);
        //保存关联标签
        for (Long tagSid:tags) {
            ArticleTagDo articleTagDo=new ArticleTagDo();
            articleTagDo.setArticleSid(articleSid);
            articleTagDo.setTagSid(tagSid);
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
            //处理标签
            List<Long> tagSids = articleDoMapper.selectTagSids(article.getSid());
            List<TagDo> tagDos = tagDoMapper.selectBySids(tagSids);
            //处理分类
            Long categorySid = articleDoMapper.selectCategorySid(article.getSid());
            CategoryDo categoryDo = categoryDoMapper.selectBySid(categorySid);
            ArticleDto articleDto=new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setTags(tagDos);
            articleDto.setCategory(categoryDo);
            articleDtoList.add(articleDto);
        }
        int total=articleDoMapper.selectTotal();
        return new PageBean<>(pageNumInt,pageSizeInt,total,articleDtoList);
    }

    @Override
    public ArticleDto selectBySid(long sid) {

        ArticleDo articleDo = articleDoMapper.selectBySid(sid);

        //处理标签
        List<Long> tagSids = articleDoMapper.selectTagSids(sid);
        List<TagDo> tagDos = tagDoMapper.selectBySids(tagSids);
        //处理分类
        Long categorySid = articleDoMapper.selectCategorySid(sid);
        CategoryDo categoryDo = categoryDoMapper.selectBySid(categorySid);
        ArticleDto articleDto=new ArticleDto();
        BeanUtils.copyProperties(articleDo,articleDto);
        articleDto.setTags(tagDos);
        articleDto.setCategory(categoryDo);

        return articleDto;
    }


}

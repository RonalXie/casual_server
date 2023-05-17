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
import org.springframework.util.ObjectUtils;

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
            ArticleDto articleDto=new ArticleDto();
            //处理标签
            List<Long> tagSids = articleDoMapper.selectTagSids(article.getSid());
            System.out.println(tagSids.toString());

            if (tagSids.size()!=0){
                List<TagDo> tagDos = tagDoMapper.selectBySids(tagSids);
                articleDto.setTags(tagDos);
            }
            //处理分类
            Long categorySid = articleDoMapper.selectCategorySid(article.getSid());
            CategoryDo categoryDo = categoryDoMapper.selectBySid(categorySid);
            BeanUtils.copyProperties(article,articleDto);
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

    @Override
    @Transactional
    public void updateBySid(ArticleDto articleDto) {
        ArticleDo articleDo=new ArticleDo();
        BeanUtils.copyProperties(articleDto,articleDo);
        articleDo.setUpdateTime(new Date());
        articleDoMapper.updateBySidSelective(articleDo);
        //更新分类关联表
        ArticleCategoryDo articleCategoryDo=new ArticleCategoryDo();
        articleCategoryDo.setArticleSid(articleDto.getSid());
        articleDoMapper.deleteArticleCategory(articleCategoryDo);
        articleCategoryDo.setCategorySid(articleDto.getCategorySid());
        articleCategoryDo.setSid(IDUtils.nextId());
        articleDoMapper.insertArticleCategory(articleCategoryDo);
        //更新标签
        ArticleTagDo articleTagDo=new ArticleTagDo();
        articleTagDo.setArticleSid(articleDto.getSid());
        articleDoMapper.deleteArticleTag(articleTagDo);
        List<Long> tagSids = articleDto.getTagSids();
        if (tagSids.size()!=0) {
            for (Long tagSid : tagSids) {
                articleTagDo.setTagSid(tagSid);
                articleTagDo.setSid(IDUtils.nextId());
                articleDoMapper.insertArticleTag(articleTagDo);
            }
        }

//        ArticleCategoryDo articleCategoryDo=new ArticleCategoryDo();
//        articleCategoryDo.setArticleSid(articleDto.getSid());
//        List<ArticleCategoryDo> articleCategoryDos = articleDoMapper.selectArticleCategory(articleCategoryDo);
//        for (ArticleCategoryDo item:articleCategoryDos
//             ) {
//            item.setCategorySid(articleDto.getCategorySid());
//            articleDoMapper.updateArticleCategory(item);
//        }
        //更新标签关联表
//        List<Long> tagSids = articleDto.getTagSids();
//        if (!ObjectUtils.isEmpty(tagSids)){
//            ArticleTagDo articleTagDo=new ArticleTagDo();
//            articleTagDo.setArticleSid(articleDto.getSid());
//            List<ArticleTagDo> articleTagDos = articleDoMapper.selectArticleTag(articleTagDo);
//            for (ArticleTagDo item:articleTagDos) {
//                for (long tagSid:tagSids
//                ) {
//                    item.setTagSid(tagSid);
//                    articleDoMapper.updateArticleTag(item);
//                }
//            }
//        }
    }
    @Override
    @Transactional
    public void deleteBySid(long sid) {
        articleDoMapper.deleteBySid(sid);
        ArticleTagDo articleTagDo=new ArticleTagDo();
        ArticleCategoryDo articleCategoryDo=new ArticleCategoryDo();
        articleTagDo.setArticleSid(sid);
        articleCategoryDo.setArticleSid(sid);
        articleDoMapper.deleteArticleTag(articleTagDo);
        articleDoMapper.deleteArticleCategory(articleCategoryDo);
    }
}

package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.ArticleTagDo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ArticleDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleDo record);

    int insertSelective(ArticleDo record);

    ArticleDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleDo record);

    int updateByPrimaryKeyWithBLOBs(ArticleDo record);

    int updateByPrimaryKey(ArticleDo record);

    int insertArticleTag(ArticleTagDo articleTagDo);
}
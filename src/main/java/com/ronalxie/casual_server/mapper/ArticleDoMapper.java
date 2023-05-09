package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.ArticleTagDo;
import com.ronalxie.casual_server.entity.TreeMenuDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


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

    List<ArticleDo> selectPage(int start,int pageSize);

    List<Long> selectTagSids(Long articleSid);

    int selectTotal();

    List<TreeMenuDo> getMenus();
}
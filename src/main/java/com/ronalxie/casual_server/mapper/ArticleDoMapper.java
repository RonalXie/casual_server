package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.ArticleCategoryDo;
import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.ArticleTagDo;
import com.ronalxie.casual_server.entity.TreeMenuDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ArticleDoMapper {
    int deleteBySid(Long sid);

    int insert(ArticleDo record);

    int insertSelective(ArticleDo record);

    ArticleDo selectByPrimaryKey(Integer id);

    ArticleDo selectBySid(long sid);

    int updateBySidSelective(ArticleDo record);

    int updateByPrimaryKeyWithBLOBs(ArticleDo record);

    int updateByPrimaryKey(ArticleDo record);

    int insertArticleTag(ArticleTagDo articleTagDo);


    int insertArticleCategory(ArticleCategoryDo articleCategoryDo);




    List<ArticleDo> selectPage(int start,int pageSize);

    List<Long> selectTagSids(Long articleSid);

    Long selectCategorySid(Long articleSid);


    int selectTotal();

    List<TreeMenuDo> getMenus();

    void deleteArticleTag(ArticleTagDo articleTagDo);

    void deleteArticleCategory(ArticleCategoryDo articleCategoryDo);

    void updateArticleTag(ArticleTagDo articleTagDo);

    void updateArticleCategory(ArticleCategoryDo articleCategoryDo);

    List<ArticleTagDo> selectArticleTag(ArticleTagDo articleTagDo);

    List<ArticleCategoryDo> selectArticleCategory(ArticleCategoryDo articleCategoryDo);


}
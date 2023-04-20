package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.ArticleDo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(ArticleDo record);

    int insertSelective(ArticleDo record);

    ArticleDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleDo record);

    int updateByPrimaryKeyWithBLOBs(ArticleDo record);

    int updateByPrimaryKey(ArticleDo record);
}
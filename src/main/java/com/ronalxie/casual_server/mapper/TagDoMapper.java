package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.TagDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TagDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagDo record);

    int insertSelective(TagDo record);

    TagDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagDo record);

    int updateByPrimaryKey(TagDo record);

    List<TagDo> selectAllTags();
}
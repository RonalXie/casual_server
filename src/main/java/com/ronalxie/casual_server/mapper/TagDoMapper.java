package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.TagDo;

public interface TagDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagDo record);

    int insertSelective(TagDo record);

    TagDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagDo record);

    int updateByPrimaryKey(TagDo record);
}
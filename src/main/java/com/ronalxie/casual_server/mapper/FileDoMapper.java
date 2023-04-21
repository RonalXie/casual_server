package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.FileDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileDo record);

    int insertSelective(FileDo record);

    FileDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileDo record);

    int updateByPrimaryKey(FileDo record);
}
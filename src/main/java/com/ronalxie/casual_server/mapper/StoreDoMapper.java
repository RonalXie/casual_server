package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.StoreDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreDo record);

    int insertSelective(StoreDo record);

    StoreDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreDo record);

    int updateByPrimaryKey(StoreDo record);
}
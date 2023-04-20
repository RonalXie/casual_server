package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.UserDo;

public interface UserDoMapper {
    int insert(UserDo record);

    int insertSelective(UserDo record);
}
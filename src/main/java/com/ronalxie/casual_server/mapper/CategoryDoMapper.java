package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.CategoryDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDoMapper {
    int deleteBySid(Long id);

    int insert(CategoryDo record);

    int insertSelective(CategoryDo record);

    CategoryDo selectByPrimaryKey(Integer id);

    CategoryDo selectBySid(Long sid);

    int updateBySidSelective(CategoryDo record);

    int updateByPrimaryKey(CategoryDo record);

    List<CategoryDo> listCategories();
}
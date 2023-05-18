package com.ronalxie.casual_server.mapper;

import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.TagDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TagDoMapper {
    int deleteBySid(Long id);

    int insert(TagDo record);

    int insertSelective(TagDo record);

    TagDo selectByPrimaryKey(Integer id);

    int updateBySidSelective(TagDo record);

    int updateByPrimaryKey(TagDo record);

    List<TagDo> selectAllTags();

    List<TagDo> selectBySids(List<Long> sids);

    List<TagDo> selectPage(int start, int pageSize, TagDo tagDo);
    int selectTotal(TagDo tagDo);



}
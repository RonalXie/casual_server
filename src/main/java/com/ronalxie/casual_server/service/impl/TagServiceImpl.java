package com.ronalxie.casual_server.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ronalxie.casual_server.entity.TagDo;
import com.ronalxie.casual_server.mapper.TagDoMapper;
import com.ronalxie.casual_server.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDoMapper tagDoMapper;


    @Override
    public void save(TagDo tagDo) {
        tagDo.setSid(IdUtil.getSnowflakeNextId());
        tagDo.setCreateTime(new Date());
        tagDoMapper.insert(tagDo);
    }

    @Override
    public List<TagDo> listTags() {
        return tagDoMapper.selectAllTags();
    }
}

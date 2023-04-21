package com.ronalxie.casual_server.service;

import com.ronalxie.casual_server.entity.TagDo;

import java.util.List;

public interface TagService {

    public void save(TagDo tagDo);

    List<TagDo> listTags();
}

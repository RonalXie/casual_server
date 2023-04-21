package com.ronalxie.casual_server.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ronalxie.casual_server.entity.ArticleDo;
import com.ronalxie.casual_server.entity.ArticleTagDo;
import com.ronalxie.casual_server.entity.dto.ArticleDto;
import com.ronalxie.casual_server.mapper.ArticleDoMapper;
import com.ronalxie.casual_server.service.ArticleService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDoMapper articleDoMapper;


    public void save(ArticleDto articleDto){

        List<Long> tags = articleDto.getTags();
        Long articleSid= IdUtil.getSnowflakeNextId();
        for (Long tagSid:tags) {
            ArticleTagDo articleTagDo=new ArticleTagDo();
            articleTagDo.setArticle_sid(articleSid);
            articleTagDo.setTag_sid(tagSid);
            articleTagDo.setSid(IdUtil.getSnowflakeNextId());
            articleDoMapper.insertArticleTag(articleTagDo);
        }
        ArticleDo articleDo=new ArticleDo();
        BeanUtils.copyProperties(articleDto,articleDo);
        articleDo.setSid(articleSid);
        articleDo.setCreateTime(new Date());
        articleDoMapper.insert(articleDo);

    }

}

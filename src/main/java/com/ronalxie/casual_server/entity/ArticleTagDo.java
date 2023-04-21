package com.ronalxie.casual_server.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagDo {

    private Integer id;
    private Long sid;
    private Long article_sid;
    private Long tag_sid;
}

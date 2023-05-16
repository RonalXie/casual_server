package com.ronalxie.casual_server.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoryDo {

    private Integer id;
    private Long sid;
    private Long articleSid;
    private Long categorySid;
}

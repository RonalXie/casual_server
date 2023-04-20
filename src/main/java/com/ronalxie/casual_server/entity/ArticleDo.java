package com.ronalxie.casual_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDo {
    private Integer id;

    private Long sid;

    private String title;

    private String abs;

    private String cover;

    private Integer views;

    private Long userSid;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String content;

}

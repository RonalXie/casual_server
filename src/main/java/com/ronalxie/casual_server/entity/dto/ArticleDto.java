package com.ronalxie.casual_server.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ronalxie.casual_server.entity.TagDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {


    private Integer id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long sid;

    private String name;

    private String abs;

    private String cover;

    private Integer views;

    private Byte top;

    private Long userSid;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String content;

    private List<Long> tags;

    private List<TagDo> tagDos;

}

package com.ronalxie.casual_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

    private int pageSize;
    private int pageNum;
    private int total;
    private List<T> pageData;

}

package com.ronalxie.casual_server.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeMenuDo {

    private int id;
    private int parentId;
    private String name;
    private String key;
    private String parentKey;
    private List<TreeMenuDo> children;
    public TreeMenuDo addChild(TreeMenuDo treeMenuDo){
        if (this.children==null){
            this.children=new ArrayList<>();
        }
        this.children.add(treeMenuDo);
        return this;
    }
}

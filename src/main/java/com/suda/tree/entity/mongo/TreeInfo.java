package com.suda.tree.entity.mongo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("TREE_INFO")
public class TreeInfo implements Serializable {

    @TableId(value = "tree_id", type = IdType.AUTO)
    private Long treeId;

    /**
     * 别名
     */
    @TableField("alias")
    private String alias;

    /**
     * 树龄
     */
    @TableField("age")
    private int age;

    /**
     * 地图横坐标
     */
    @TableField("map_x")
    private double mapX;

    /**
     * 地图纵坐标
     */
    @TableField("map_y")
    private double mapY;







}

package com.suda.tree.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Roles {

    @TableId(type = IdType.AUTO)
    private Long roleId;

    private String role;
}

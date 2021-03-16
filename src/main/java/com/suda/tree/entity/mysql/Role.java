package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色
 */
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    private Long Id;

    @Column(name = "role")
    private String role;

}

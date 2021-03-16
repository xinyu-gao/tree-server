package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户、角色对应表
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

}

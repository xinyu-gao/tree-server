package com.suda.tree.entity.mysql;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 用户信息
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "creat_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creatTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    /**
     * 可用状态, 默认为 1（可用），置 0 不可用
     */
    @Column(name = "status", columnDefinition = "int default 1")
    private String status;

}
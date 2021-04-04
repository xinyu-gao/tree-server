package com.suda.tree.entity.mysql;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.suda.tree.util.JpaConverterListJson;
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
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "avatar")
    private String avatar;

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

    @Column(name = "roles", columnDefinition = "TEXT")
    @Convert(converter = JpaConverterListJson.class)
    private List<String> roles;

    public User(String username) {
        this.username = username;
    }

    public User() {

    }
}
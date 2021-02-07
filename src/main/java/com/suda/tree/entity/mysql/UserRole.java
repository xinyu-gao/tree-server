package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    private Long id;

    private Long userId;

    private Long roleId;

}

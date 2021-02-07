package com.suda.tree.entity.mysql;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="node_info")
public class NodeInfo implements Serializable {

    @Id
    private Long id;

}

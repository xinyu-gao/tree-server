package com.suda.tree.entity.mysql;

import com.suda.tree.util.JpaConverterListJson;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "tree_picture")
public class TreePicture implements Serializable {

    public TreePicture(String treeId, List<String> picUrls) {
        this.treeId = treeId;
        this.picUrls = picUrls;
    }

    public TreePicture() {

    }

    @Id
    @Column(name = "tree_id")
    private String treeId;


    /**
     * 图片地址
     */
    @Column(name = "pic_urls", columnDefinition = "TEXT")
    @Convert(converter = JpaConverterListJson.class)
    private List<String> picUrls;

}

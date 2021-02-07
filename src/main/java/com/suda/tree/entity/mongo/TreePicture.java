package com.suda.tree.entity.mongo;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@Document(collection = "tree_picture")
public class TreePicture implements Serializable {

    @Id
    @Field(name = "tree_id")
    @Indexed(unique = true)
    @NotNull
    private String treeId;

    public TreePicture(@NotNull String treeId, @NotNull Set<String> picUrls) {
        this.treeId = treeId;
        this.picUrls = picUrls;
    }

    /**
     * 图片地址
     */
    @Field(name = "pic_urls")
    @NotNull
    private Set<String> picUrls;
}

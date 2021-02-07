package com.suda.tree.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Document(collection = "tree_history")
public class TreeHistory {

    public TreeHistory(@NotNull String treeId, @NotNull String history) {
        this.treeId = treeId;
        this.history = history;
    }

    @Id
    @Field(name = "tree_id")
    @Indexed(unique = true)
    @NotNull
    private String treeId;

    /**
     * 古树历史（300以内）
     */
    @Field(name = "history")
    @NotNull
    private String history;
}

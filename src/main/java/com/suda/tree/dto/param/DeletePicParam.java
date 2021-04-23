package com.suda.tree.dto.param;

import lombok.Data;

@Data
public class DeletePicParam {

    private String treeId;

    private String fileName;

    private String fileURL;

    private String username;
}

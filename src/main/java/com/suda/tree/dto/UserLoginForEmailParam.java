package com.suda.tree.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginForEmailParam implements Serializable {

    private String email;

    private String code;
}

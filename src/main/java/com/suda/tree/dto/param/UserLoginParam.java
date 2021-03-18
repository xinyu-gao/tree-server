package com.suda.tree.dto.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserLoginParam implements Serializable {

    private String username;

    private String password;

    private String phoneNumber;

    private String email;

    private List<String> roles;
}

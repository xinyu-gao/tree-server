package com.suda.tree.service;

import com.suda.tree.dto.PageResult;
import com.suda.tree.entity.mysql.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    PageResult<User> findAllForPage(int size, int page);

    User findByUsername(String username);

    List<String> findRolesByUsername(String username);

    void saveUser(User user);

    boolean updatePassword(User user);

    boolean updateUserRoles(User user);
}

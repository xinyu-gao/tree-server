package com.suda.tree.service;

import com.suda.tree.dto.result.PageResult;
import com.suda.tree.entity.mysql.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    List<User> findAll();

    PageResult<User> findAllForPage(int size, int page);

    User findByUsername(String username);

    List<String> findRolesByUsername(String username);

    boolean saveUser(User user);

    boolean updatePassword(User user);

    boolean updateEmail(User user);

    boolean updatePhoneNumber(User user);

    boolean updateUserRoles(User user);

    String login(String username, String password);

    String loginForEmail(String Email, String validateCode);

    String logout(HttpServletRequest request) throws Exception;

    UserDetails loadUserByUsername(String username);

    String findUsernameByEmail(String Email);

    PageResult<User> getUserListSorted(int page, int size, String[] keys, int asc);

    PageResult<User> getUserList(int page, int size);
}

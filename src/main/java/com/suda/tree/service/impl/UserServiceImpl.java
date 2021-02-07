package com.suda.tree.service.impl;

import com.suda.tree.dao.UserRepository;
import com.suda.tree.dto.PageResult;
import com.suda.tree.entity.mysql.User;
import com.suda.tree.service.UserService;
import com.suda.tree.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public PageResult<User> findAllForPage(int page, int size) {
        return PageUtil.setResult(userRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<String> findRolesByUsername(String username) {
        return userRepository.findRolesByUsername(username);
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public boolean updatePassword(User user) {
        return 1 == userRepository.updatePasswordByUsername(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean updateUserRoles(User user) {
        return 1 == userRepository.updatePasswordByUsername(user.getUsername(), user.getPassword());
    }

}

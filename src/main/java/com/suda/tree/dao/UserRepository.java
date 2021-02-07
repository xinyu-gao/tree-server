package com.suda.tree.dao;

import com.suda.tree.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select r.role from user u, role r, user_role ur where u.username = ?1 and u.user_id = ur.user_id and r.role_id = ur.role_id")
    List<String> findRolesByUsername(String username);

    User findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update user set password = :password where username = :username")
    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);

}

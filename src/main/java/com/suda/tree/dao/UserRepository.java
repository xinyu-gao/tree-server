package com.suda.tree.dao;

import com.suda.tree.entity.mysql.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update user set password = :password where username = :username")
    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update user set email = :email where username = :username")
    int updateEmailByUsername(@Param("username") String username, @Param("email") String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update user set phone_number = :phone_numberwhere username = :username")
    int updatePhoneByUsername(@Param("username") String username, @Param("phone_number") String phoneNumber);

    @Query(nativeQuery = true, value = "select username from user where email = ?1")
    String findUsernameByEmail(@Param("email") String Email);

    @Override
    <S extends User> S save(S s);

    @Override
    Page<User> findAll(Pageable pageable);
}

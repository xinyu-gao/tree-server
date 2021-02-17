package com.suda.tree.service.impl;

import com.suda.tree.dao.UserRepository;
import com.suda.tree.dto.result.PageResult;
import com.suda.tree.entity.AdminUserDetails;
import com.suda.tree.entity.mysql.User;
import com.suda.tree.exception.CacheException;
import com.suda.tree.service.RedisService;
import com.suda.tree.service.UserService;
import com.suda.tree.service.ValidateCodeService;
import com.suda.tree.util.JwtTokenUtil;
import com.suda.tree.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.blacklist}")
    private String blackListKeyForJWT;

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
    public boolean saveUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()) == null) {
            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePassword(User user) {
        return 1 == userRepository.updatePasswordByUsername(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean updateUserRoles(User user) {
        return 1 == userRepository.updatePasswordByUsername(user.getUsername(), user.getPassword());
    }

    @Override
    public String login(String username, String password) {
        if (findByUsername(username) == null) {
            throw new BadCredentialsException("该用户名未注册");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String loginForEmail(String Email, String validateCode) {
        if (!validateCodeService.validate(Email, validateCode)) throw new BadCredentialsException("验证码不正确");
        String username = findUsernameByEmail(Email);
        if (username == null) throw new BadCredentialsException("该邮箱地址未注册");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    @CacheException
    public String logout(HttpServletRequest request) throws Exception {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());
            // jwt 黑名单
            boolean isInBlackList = redisService.lGetAll(blackListKeyForJWT).contains(authToken);
            if (!isInBlackList) {
                redisService.lPush(blackListKeyForJWT, authToken, 604800);
            }
            return "登出成功";
        } else {
            throw new Exception("token 格式不正确");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        User user = getAdminByUsername(username);
        if (user != null) {
            //获取用户的资源信息
            List<String> resourceList = findRoles(username);
            return new AdminUserDetails(user, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @CacheException
    private User getAdminByUsername(String username) {
        // 先从缓存中获取数据
        User user = (User) redisService.get(username);
        if (user == null) {
            //缓存中没有从数据库中获取
            user = findByUsername(username);
            //将数据库中的数据存入缓存中
            redisService.set(username, user, 604800);
        }
        return user;
    }

    @CacheException
    private List<String> findRoles(String username) {
        // 先从缓存中获取数据
        List<String> roles = (List<String>) redisService.get(username + "_roles");
        if (roles == null) {
            //缓存中没有从数据库中获取
            roles = findRolesByUsername(username);
            //将数据库中的数据存入缓存中
            redisService.set(username + "_roles", roles, 604800);
        }
        return roles;
    }

    @Override
    public String findUsernameByEmail(String Email) {
        return userRepository.findUsernameByEmail(Email);
    }

}

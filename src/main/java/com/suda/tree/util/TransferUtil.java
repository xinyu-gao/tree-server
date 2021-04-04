package com.suda.tree.util;

import ch.qos.logback.core.encoder.EchoEncoder;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.suda.tree.dto.param.NodeParam;
import com.suda.tree.entity.mysql.ImsiInfo;
import com.suda.tree.entity.mysql.ImsiInfoHistory;
import com.suda.tree.entity.mysql.User;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class TransferUtil {

    @Autowired
    private static PasswordEncoder passwordEncoder;

    public static ImsiInfo NodeParamToImsiInfo(NodeParam nodeParam){
        ImsiInfo imsiInfo = new ImsiInfo();
        imsiInfo.setImsi(nodeParam.getImsi());
        imsiInfo.setTemp(nodeParam.getTemp());
        imsiInfo.setHumidity(nodeParam.getHumidity());
        imsiInfo.setSlant(nodeParam.getSlant());
        imsiInfo.setOnline(true);
        imsiInfo.setSendTime(DateUtil.date());
        return imsiInfo;
    }
    public static ImsiInfoHistory NodeParamToImsiInfoHistory(NodeParam nodeParam){
        ImsiInfoHistory imsiInfoHistory = new ImsiInfoHistory();
        imsiInfoHistory.setImsi(nodeParam.getImsi());
        imsiInfoHistory.setTemp(nodeParam.getTemp());
        imsiInfoHistory.setHumidity(nodeParam.getHumidity());
        imsiInfoHistory.setSlant(nodeParam.getSlant());
        imsiInfoHistory.setSendTime(DateUtil.date());
        return imsiInfoHistory;
    }

    public static User alipayAuthToSysUser(AuthResponse authResponse) {
        User user = new User();
        JSONObject s = JSONUtil.parseObj(authResponse.getData());
        user.setUserId(s.getStr("uuid"));
        user.setUsername(getUsernameFromAuth(authResponse));
        // 6 位随意密码，加密
        user.setPassword(passwordEncoder.encode(RandomUtil.randomString(6)));
        user.setEmail(s.getStr("email"));
        user.setRoles(Collections.singletonList("user"));
        log.info(user.toString());
        return user;
    }

    public static String getUserIdFromAuth(AuthResponse authResponse) {
        JSONObject s = JSONUtil.parseObj(authResponse.getData());
        return s.getStr("uuid");
    }

    public static String getUsernameFromAuth(AuthResponse authResponse) {
        JSONObject s = JSONUtil.parseObj(authResponse.getData());
        return s.getStr("username").length() > 0 ? s.getStr("username") : s.getStr("nickname");
    }


}

package com.suda.tree.service;

import com.suda.tree.entity.WebSocketLogInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 当接收到监测节点发送的数据时，需要通知前端获取最新数据
 * @author xinyu
 */
@Slf4j
@Service
@Data
@ServerEndpoint(value = "/imsi")
public class WebSocketToIMSIService {

    private final static List<WebSocketToIMSIService> webSocketList = new CopyOnWriteArrayList<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen( Session session) throws IOException {
        session.setMaxIdleTimeout(1000000);
        this.session = session;
        webSocketList.add(this);
        sendInfoForAll("String message");
        log.info("imsi websocket连接");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("imsi websocket关闭连接");
    }


    /**
     * @param session 当前对话
     * @param error   错误信息
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("imsi websocket连接发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动群发消息
     *
     * @param message 想要发送的消息
     * @throws IOException     发送失败
     */
    public static void sendInfoForAll(String message) throws IOException{
        log.info("websocket群发消息，消息：" + message);
        for (WebSocketToIMSIService item : webSocketList) {
            item.session.getBasicRemote().sendText(message);
        }
    }

}

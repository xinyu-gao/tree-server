package com.suda.tree.service;

import com.suda.tree.entity.WebSocketLogInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author xinyu
 * '@ServerEndpoint' 这个注解用于标识作用在类上
 * 它的主要功能是把当前类标识成一个 WebSocket 的服务端
 * 注解的值用户客户端连接访问的 URL 地址
 */
@Slf4j
@Service
@Data
@ServerEndpoint("/ws")
public class WebSocketService {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    /**
     * concurrent 包的线程安全 Set，用来存放每个客户端对应的 WebSocket 对象。
     */
    private final CopyOnWriteArraySet<WebSocketService> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        this.session = session;
        webSocketSet.add(this);
        log.info(WebSocketLogInfo.buildAndToString(session.getId(), authentication,"websocket连接"));
        sendInfoForOne("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info(WebSocketLogInfo.buildAndToString(session.getId(), authentication,"websocket关闭连接"));
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) throws IOException, EncodeException {
        log.info(WebSocketLogInfo.buildAndToString(session.getId(), authentication,"收到消息："+  message));
        sendInfoForOne("hello");
    }

    /**
     * @param session 当前对话
     * @param error   错误信息
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info(WebSocketLogInfo.buildAndToString(session.getId(), authentication,"发生错误"));
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送，单发消息
     *
     * @param message 想要发送的消息
     * @throws IOException     发送失败
     * @throws EncodeException Object 转 JSON 失败
     */
    public void sendInfoForOne(String message) throws IOException, EncodeException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 实现服务器主动群发消息
     *
     * @param message 想要发送的消息
     * @throws IOException     发送失败
     * @throws EncodeException Object 转 JSON 失败
     */
    public void sendInfoForAll(String message) throws IOException, EncodeException {
        log.info("websocket群发消息，消息：" + message);
        for (WebSocketService item : webSocketSet) {
            item.sendInfoForOne(message);
        }
    }

}

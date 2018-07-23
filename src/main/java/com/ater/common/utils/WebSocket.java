package com.ater.common.utils;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
@Slf4j
@ServerEndpoint(value = "/websocket/{userId}")
 @Component
public class WebSocket  {    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocket > webSocketSet = new CopyOnWriteArraySet<WebSocket >();
    private static final Logger log = Logger.getLogger(WebSocket .class);
    /*每个浏览器连接都会有一个新的会话对象*/
    private Session session;
    /*用来存储每个会话的session,静态的不会被实例化*/
    private static CopyOnWriteArraySet<WebSocket> webSocketSets = new CopyOnWriteArraySet<>();
    //当前客户端用户编号
    private String userId = "";
    /**
     * 主要用来监听连接建立，config用来获取WebsocketConfig中的配置信息
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String param,Session session, EndpointConfig config) {
        userId=param;//接收到发送消息的人员编号
        this.session = session;
        webSocketSets.add(this);
        log.info("【websocket消息】有新的连接, 总数:{}"+ webSocketSets.size());
    }

    @OnClose
    public void onClose() {
        if (!userId.equals("")) {
            webSocketSets.remove(this);
            log.info("【websocket消息】连接断开, 总数:{}" + webSocketSets.size());
        }
    }

    @OnError
    public void onError(Throwable e, Session session) {
        webSocketSets.remove(this);
        log.info("【websocket消息】连接出错或未关闭socket：" + e.getMessage());

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        for(WebSocket ws:webSocketSets){
            ws.session.getAsyncRemote().sendText("广播："+message);
        }
        log.info("【websocket消息】收到客户端发来的消息:{}"+message);
    }



    public void sendMessage(String message,String userId1) throws IOException {
        if(userId1.equals(userId)){
            this.session.getBasicRemote().sendText(message);
        }

    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,String userId1) throws IOException {
        log.info(message);
        for (WebSocket  item : webSocketSets) {
            try {
                item.sendMessage(message,userId1);
            } catch (IOException e) {
                continue;
            }
        }
    }

}
package com.chatapp.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by BRF on 2017-04-06.
 */
public class ChatWebSocketClient extends WebSocketClient {

    Logger logger = Logger.getLogger(ChatWebSocketClient.class.getName());

    public ChatWebSocketClient(URI uri) {
        super(uri);
    }

    public ChatWebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
        super(uri, draft, map, i);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("连接成功");
    }

    @Override
    public void onMessage(String s) {
        logger.info("收到消息了:"+s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.info("连接关闭");
    }

    @Override
    public void onError(Exception e) {
        logger.info("连接出错");
    }
}

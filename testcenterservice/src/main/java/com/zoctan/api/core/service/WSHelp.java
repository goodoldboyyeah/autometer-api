package com.zoctan.api.core.service;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

@ClientEndpoint
public class WSHelp {
    private Session userSession = null;

    public WSHelp(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) throws IOException {
        System.out.println("WebSocket 连接已打开");
        this.userSession = userSession;
        // 发送消息到指定地址
        String requestUrl = "ws://127.0.0.1/hello";
        String message = "{\"url\": \"" + requestUrl + "\", \"message\": \"Hello WebSocket\"}";
        userSession.getBasicRemote().sendText(message);
        userSession.getAsyncRemote().sendBinary(ByteBuffer.wrap("Hello WebSocket in binary".getBytes()));

        // 发送二进制数据
//        byte[] binaryData = "Hello WebSocket in binary".getBytes();
//        try {
//            userSession.getBasicRemote().sendBinary(ByteBuffer.wrap(binaryData));
//        } catch (IOException e) {
//            System.out.println("发送二进制数据时出错: " + e.getMessage());
//        }
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) throws IOException {
        userSession.close();
        System.out.println("WebSocket 连接已关闭");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到消息: " + message);
    }


    public void sendTextMessage(String url, String message) throws IOException {
        userSession.getBasicRemote().sendText(message);
        userSession.getAsyncRemote().sendBinary(ByteBuffer.wrap("Hello WebSocket in binary".getBytes()));
    }

    public void sendBinaryMessage(String url, String message) throws IOException {
        userSession.getAsyncRemote().sendBinary(ByteBuffer.wrap("Hello WebSocket in binary".getBytes()));
    }

    public static void main(String[] args) {
        try {
            URI uri = new URI("ws://127.0.0.1/wso");
            WSHelp client = new WSHelp(uri);
            client.sendTextMessage("ws://127.0.0.1/hello", "Hello WebSocket");
            client.sendBinaryMessage("ws://127.0.0.1/hello", "Hello WebSocket");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}



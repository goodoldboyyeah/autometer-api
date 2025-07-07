package com.zoctan.api.core.service;

public class WSCallBackHelp implements MessageCallback  {
    private final WSHelp wsHelp;

    public WSCallBackHelp(WSHelp wsHelp) {
        this.wsHelp = wsHelp;
        // 设置回调函数
//        wsHelp.setCallback(this);
    }

    @Override
    public void onMessageReceived(String message) {
        System.out.println("SomeOtherClass 收到消息: " + message);
        // 处理消息
    }
}

//WSHelp

package com.awu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class) //可以理解为我们定义一个消息消费者的接收管道
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT) //输入源：作为一个消息监听者
    public void input(Message<String> message){
        //获取到消息
        String messageStr = message.getPayload();

        System.out.println("消费者1号----->接收到的消息： "+messageStr+"\t" +
                "port: " + serverPort);
    }
}

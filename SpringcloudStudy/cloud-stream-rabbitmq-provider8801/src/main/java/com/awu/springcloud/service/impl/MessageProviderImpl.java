package com.awu.springcloud.service.impl;

import com.awu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * 注意点: 这里不需要使用注解@Service了,需要与 Stream 来交互，业务逻辑基于Stream
 * 使用 @EnableBinding 定义消息生产者的发送管道
 */

@EnableBinding(Source.class)   // 定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;   //消息发送管道

    @Override
    public String send() {
        String serial= UUID.randomUUID().toString();  //流水号，随机
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("------serial: " +serial );
        return null;
    }
}

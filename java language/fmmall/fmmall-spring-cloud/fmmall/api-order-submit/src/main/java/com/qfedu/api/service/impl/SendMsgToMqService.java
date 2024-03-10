package com.qfedu.api.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMsgToMqService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void sendMsg(String msg) {
        amqpTemplate.convertAndSend("ex1", "key1", msg);
    }
}

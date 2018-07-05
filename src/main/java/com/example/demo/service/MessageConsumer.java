package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues="${demo.rabbitmq.queue}")
    public void receive( final Message message) {
        System.out.println("Received JSON message : " + message.toString());
    }
}

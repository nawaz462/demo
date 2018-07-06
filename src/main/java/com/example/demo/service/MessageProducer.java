package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.repopsitory.TransactionRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@EnableScheduling
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    @Value("${demo.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    private TransactionRepo  transactionRepo;

    ExecutorService senderThread = Executors.newFixedThreadPool(10);

    //for demo purpose sending messages at 5 sec interval
    @Scheduled(fixedRate = 5000 * 60)
    public void sendMessage() {

        List<Transaction> transactionData =  transactionRepo.findAll();
        transactionData.stream().forEach((Transaction s) -> {
            senderThread.execute(new Runnable() {
                @Override
                public void run() {
                    ObjectMapper mapperObj = new ObjectMapper();
                    String jsonStr = null;
                    try {
                        jsonStr = mapperObj.writeValueAsString(s);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Published JSON String :: " + jsonStr);
                    rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, jsonStr);
                }
            });
        });
    }
}

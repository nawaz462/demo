package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.repopsitory.TransactionRepo;
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

    int messageNumber = 1;

    //for demo purpose sending messages at 5 sec interval
    @Scheduled(fixedRate = 5000)
    public void sendMessage() {

        ExecutorService senderThread = Executors.newFixedThreadPool(10);
        List<Transaction> transactionData =  transactionRepo.findAll();
        transactionData.stream().forEach(s -> System.out.println(s.toString()));
        System.out.println("Records in DB : "+transactionData.size());

        //Below commented lines should be kept under list iteration with message Transaction obj as Json string
        /*senderThread.execute(new Runnable() {
            @Override
            public void run() {
                rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, "---- Test Message " + ++messageNumber);
                System.out.println("Published message : " + "---- Test Message " + messageNumber);
            }
        });*/

        //For testing queues message transmission purpose use below code
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, "---- Test Message " + ++messageNumber);
        System.out.println("Published message : " + "---- Test Message " + messageNumber);
    }
}

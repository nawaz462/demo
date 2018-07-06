package com.example.demo.service;

import com.example.demo.entity.DummyTransaction;
import com.example.demo.entity.Transaction;
import com.example.demo.repopsitory.DummyTransactionRepo;
import com.example.demo.repopsitory.TransactionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageConsumer {

    @Autowired
    private DummyTransactionRepo dummyTransactionRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @RabbitListener(queues="${demo.rabbitmq.queue}")
    public void receive( String message) {

        System.out.println("Received JSON message : " + message);
        ObjectMapper mapper = new ObjectMapper();
        DummyTransaction dummyTransactionData = null;

        try {
            dummyTransactionData = mapper.readValue(message, DummyTransaction.class);

            //save into secondary Dummy table
            dummyTransactionRepo.saveAndFlush(dummyTransactionData);

            //Calling stored procedure to say MARK complete to source
            dummyTransactionRepo.markStatusProcedureCall(dummyTransactionData.getTransactionId());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.demo.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageProducerConfig {

    @Value("demo.rabbitmq.exchange")
    private String topicExchange;

    @Bean
    public TopicExchange senderTopicExchange() {
        return new TopicExchange(topicExchange);
    }

}

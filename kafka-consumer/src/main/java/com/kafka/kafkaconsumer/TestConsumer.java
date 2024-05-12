package com.kafka.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @KafkaListener(topics="topic", groupId = "group_1")
    public void listen(String message) {
        System.out.println(message);
    }
}

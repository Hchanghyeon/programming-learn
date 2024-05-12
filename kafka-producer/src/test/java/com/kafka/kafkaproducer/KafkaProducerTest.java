package com.kafka.kafkaproducer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaProducerTest {

    @Autowired
    private TestProducer testProducer;

    @Test
    void test() {
        testProducer.create();
    }
}

package com.adidas.serenitySteps;

import com.adidas.kafka.consumer.Consumer;
import com.adidas.kafka.producer.Producer;
import com.adidas.kafka.util.KafkaProperties;
import net.thucydides.core.annotations.Step;

public class ExampleSteps {

    @Step
    public void producterSendMessage(String topic) {
        boolean isAsync = true;
        Producer producerThread = new Producer(topic, isAsync);
        producerThread.start();
    }

    @Step
    public void consumerReceiveMessage(String topic) {
        Consumer consumerThread = new Consumer(topic);
        consumerThread.start();
    }
}

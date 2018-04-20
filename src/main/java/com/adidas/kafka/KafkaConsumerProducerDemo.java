package com.adidas.kafka;

import com.adidas.kafka.consumer.Consumer;
import com.adidas.kafka.producer.Producer;
import com.adidas.kafka.util.KafkaProperties;

public class KafkaConsumerProducerDemo {
    public static void main(String[] args) {
        //boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
        //Producer producerThread = new Producer(KafkaProperties.TOPIC, isAsync);
        boolean isAsync = true;
        Producer producerThread = new Producer(KafkaProperties.TOPIC, isAsync);
        producerThread.start();

        Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);
        consumerThread.start();

    }
}

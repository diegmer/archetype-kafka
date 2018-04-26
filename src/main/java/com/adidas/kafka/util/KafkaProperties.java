package com.adidas.kafka.util;

public class KafkaProperties {
    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";
    public static final String KAFKA_PRODUCTER_CLIENT_ID = "KafkaExampleProducer";
    public static final String KAFKA_CONSUMER_CLIENT_ID = "KafkaExampleConsumer";

    private KafkaProperties() {
    }
}
package com.adidas.serenitySteps;

import com.adidas.kafka.connection.KafkaConnection;
import com.adidas.kafka.connection.ZooKeeperConnection;
import com.adidas.kafka.consumer.Consumer;
import com.adidas.kafka.producer.Producer;
import net.thucydides.core.annotations.Step;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Assert;

import java.io.IOException;

public class ExampleSteps {

    @Step
    public void kakfaServerIsUp(String kafkaNode) {
        KafkaConnection kafkaConnection = new KafkaConnection();
        Assert.assertTrue("Kafka Node is down " + kafkaNode, kafkaConnection.isUp(kafkaNode));
    }

    @Step
    public void zooKeeperServerIsUp(String zookeeperNode) throws IOException, InterruptedException {
        ZooKeeperConnection zooKeeperConnection = new ZooKeeperConnection();
        ZooKeeper zooKeeper = zooKeeperConnection.connect(zookeeperNode);
        if (!zooKeeper.getState().name().equalsIgnoreCase("CONNECTED")) {
            Assert.fail("Server Zookeeper is down in url: " + zookeeperNode);
        }
    }

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

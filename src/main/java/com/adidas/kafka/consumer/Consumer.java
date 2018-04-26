package com.adidas.kafka.consumer;

import com.adidas.kafka.util.KafkaProperties;
import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.junit.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Consumer extends ShutdownableThread {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;

    public Consumer(String topic, String group) {
        super(KafkaProperties.KAFKA_PRODUCTER_CLIENT_ID, false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        //A unique string that identifies the consumer group this consumer belongs to
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        //If true the consumer's offset will be periodically committed in the background.
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //The frequency in milliseconds that the consumer offsets are auto-committed to Kafka
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //The timeout used to detect consumer failures when using Kafka's group management facility
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //Consumer is constructed using a Properties file
        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        //poll() -> it returns records written to Kafka that consumers in our group have not read yet
        //ConsumerRecords<Integer, String> records = consumer.poll(10000);
        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        if (!records.isEmpty()) {
            for (ConsumerRecord<Integer, String> record : records) {
                System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
            }
        } else {
            Assert.fail("There is not records to read");
        }

    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }

    /**
     * @return
     */
    public Map<String, List<PartitionInfo>> getListTopics() {
        return consumer.listTopics();
    }


}

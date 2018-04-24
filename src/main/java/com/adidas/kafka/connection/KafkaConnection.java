package com.adidas.kafka.connection;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class KafkaConnection {


    public KafkaConnection() {
    }

    /**
     * Check if a Kafka server is running is to create a simple before to create a simple Consumer pointing
     *
     * @param kafkaNode -> url + port Kafka server
     * @return true -> if is up and false if not
     */
    public boolean isUp(String kafkaNode) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaNode);
        properties.put("connections.max.idle.ms", 10000);
        properties.put("request.timeout.ms", 5000);
        try (AdminClient client = KafkaAdminClient.create(properties)) {
            ListTopicsResult topics = client.listTopics();
            Set<String> names = topics.names().get();
            if (names.isEmpty()) {
                // case: if no topic found.
                return false;
            }
            return true;
        } catch (InterruptedException | ExecutionException e) {
            // Kafka is not available
            return false;
        }
    }
}

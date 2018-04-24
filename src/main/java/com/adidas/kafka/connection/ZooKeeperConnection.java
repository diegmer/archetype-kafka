package com.adidas.kafka.connection;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZooKeeperConnection {

    // Local Zookeeper object to access ZooKeeper ensemble
    private ZooKeeper zoo;
    final CountDownLatch connectionLatch = new CountDownLatch(1);

    /**
     *
     */
    public ZooKeeperConnection() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Initialize the Zookeeper connection
     *
     * @param host
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ZooKeeper connect(String host) throws IOException,
            InterruptedException {

        zoo = new ZooKeeper(host, 2000, new Watcher() {

            public void process(WatchedEvent we) {

                if (we.getState() == Event.KeeperState.SyncConnected) {
                    connectionLatch.countDown();
                }
            }
        });

        connectionLatch.await(50000, TimeUnit.MILLISECONDS);

        return zoo;
    }

    // Method to disconnect from zookeeper server
    public void close() throws InterruptedException {
        zoo.close();
    }

}
Feature: Kafka integration with Serenity

  Scenario Outline: Firt Producter and Consumer
    #Given kafka cluster has kafka nodes "192.168.99.100:9092" and zookeeper nodes "192.168.99.100:2181"
    #Given kafka cluster has kafka nodes "localhost:9092" and zookeeper nodes "localhost:2181"
    Given kafka cluster has kafka nodes "<kafkaBroker>" and zookeeper nodes "<zookeeperNode>"
    When a producer sends a message to "<nameTopic>"
    Then a consumer receives a message from "<nameTopic>" in group "<groupConsumer>"
#    Then I view topic called "topic6"

    Examples:
      | kafkaBroker    | zookeeperNode  | nameTopic | groupConsumer |
      | localhost:9092 | localhost:2181 | topic3    | g3            |
#      | deheremap7213.emea.adsint.biz:9092  | deheremap7213.emea.adsint.biz:2181  |
#      | deheremap7373.emea.adsint.biz:9092  | deheremap7373.emea.adsint.biz:2181  |
#      | deheremap7670.linux.adsint.biz:9092 | deheremap7670.linux.adsint.biz:2181 |
Feature: Kafka integration with Serenity

  Scenario Outline: Firt Producter and Consumer
    #Given kafka cluster has kafka nodes "192.168.99.100:9092" and zookeeper nodes "192.168.99.100:2181"
    #Given kafka cluster has kafka nodes "localhost:9092" and zookeeper nodes "localhost:2181"
    Given kafka cluster has kafka nodes "<kafkabroker>" and zookeeper nodes "eheremap7213.emea.adsint.biz:2181"
#
#    When a producer sends a message to "topic-test"
#    Then a consumer receives a message from "topic-test" in group "group-test"

    Examples:

      | kafkabroker                         |
      | localhost:9092                      |
      | deheremap7213.emea.adsint.biz:9092  |
      | deheremap7373.emea.adsint.biz:9092  |
      | deheremap7670.linux.adsint.biz:9092 |
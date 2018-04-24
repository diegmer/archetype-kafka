package com.adidas.gherkinDefinitions;

import com.adidas.serenitySteps.ExampleSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ExampleDefinitions {

    @Steps
    public ExampleSteps exampleSteps;

    @Given("^kafka cluster has kafka nodes \"([^\"]*)\" and zookeeper nodes \"([^\"]*)\"$")
    public void kafkaClusterHasKafkaNodesAndZookeeperNodes(String kafkaNode, String zookeeperNode) throws Throwable {
        exampleSteps.kakfaServerIsUp(kafkaNode);
        //exampleSteps.zooKeeperServerIsUp(zookeeperNode);
    }

    @When("^a producer sends a message to \"([^\"]*)\"$")
    public void aProducerSendsAMessageTo(String topic) throws Throwable {
        exampleSteps.producterSendMessage(topic);
    }

    @Then("^a consumer receives a message from \"([^\"]*)\" in group \"([^\"]*)\"$")
    public void aConsumerReceivesAMessageFromInGroup(String topic, String group) throws Throwable {
        exampleSteps.consumerReceiveMessage(topic);
    }

}

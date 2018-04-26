package com.adidas.gherkinDefinitions;

import com.adidas.serenitySteps.ExampleSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ExampleDefinitions {

    @Steps
    public ExampleSteps exampleSteps;

    @Given("^kafka cluster has kafka nodes \"([^\"]*)\" and zookeeper nodes \"([^\"]*)\"$")
    public void kafkaClusterHasKafkaNodesAndZookeeperNodes(String kafkaNode, String zookeeperNode) throws Throwable {
        exampleSteps.zooKeeperServerIsUp(zookeeperNode);
        exampleSteps.kakfaServerIsUp(kafkaNode);
    }

    @When("^a producer sends a message to \"([^\"]*)\"$")
    public void aProducerSendsAMessageTo(String topic) throws Throwable {
        exampleSteps.producterSendMessage(topic);
    }

    @Then("^a consumer receives a message from \"([^\"]*)\" in group \"([^\"]*)\"$")
    public void aConsumerReceivesAMessageFromInGroup(String topic, String group) throws Throwable {
        exampleSteps.consumerReceiveMessage(topic, group);
    }

    @Then("^I view topic called \"([^\"]*)\"$")
    public void iViewTopicName(String topic) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        exampleSteps.viewTopic(topic);
    }
}

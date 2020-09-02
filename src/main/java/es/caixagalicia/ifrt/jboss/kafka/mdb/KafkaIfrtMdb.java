package es.caixagalicia.ifrt.jboss.kafka.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jboss.ejb3.annotation.ResourceAdapter;

import fish.payara.cloud.connectors.kafka.api.KafkaListener;
import fish.payara.cloud.connectors.kafka.api.OnRecord;

@MessageDriven(name="MDB_KAFKA_TEST",activationConfig = {
		@ActivationConfigProperty(propertyName = "clientId", propertyValue = "KafkaJCAClient"),
		@ActivationConfigProperty(propertyName = "groupIdConfig", propertyValue = "myGroup"),
		@ActivationConfigProperty(propertyName = "topics", propertyValue = "mytopic"),
		@ActivationConfigProperty(propertyName = "bootstrapServersConfig", propertyValue = "localhost:9092"),
		@ActivationConfigProperty(propertyName = "retryBackoff", propertyValue = "1000"),
		@ActivationConfigProperty(propertyName = "autoCommitInterval", propertyValue = "100"),
		@ActivationConfigProperty(propertyName = "keyDeserializer", propertyValue = "org.apache.kafka.common.serialization.StringDeserializer"),
		@ActivationConfigProperty(propertyName = "valueDeserializer", propertyValue = "org.apache.kafka.common.serialization.StringDeserializer"),
		@ActivationConfigProperty(propertyName = "pollInterval", propertyValue = "3000"),
		@ActivationConfigProperty(propertyName = "commitEachPoll", propertyValue = "true"),
		@ActivationConfigProperty(propertyName = "useSynchMode", propertyValue = "true") })
@ResourceAdapter(value = "kafka")
public class KafkaIfrtMdb implements KafkaListener {

	public KafkaIfrtMdb() {
		System.out.println("Bean instance created");
	}

	@OnRecord(topics = { "mytopic" })
	public void getMessage(ConsumerRecord record) {
		System.out.println("> Got record on topic test " + record);
	}

}
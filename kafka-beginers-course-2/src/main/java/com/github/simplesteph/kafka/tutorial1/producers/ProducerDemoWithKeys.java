package com.github.simplesteph.kafka.tutorial1.producers;

import lombok.val;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoWithKeys {
    Logger log = LoggerFactory.getLogger(ProducerDemoWithKeys.class);
    private String bootstrapServer = "127.0.0.1:9092";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ProducerDemoWithKeys obj = new ProducerDemoWithKeys();
        obj.run();
    }

    void run() throws ExecutionException, InterruptedException {
        // Create properties
        val props = getProps(bootstrapServer);
        // Create producer
        val producer = new KafkaProducer<String, String>(props);

        val topicName = "first_topic";

        // create a producer record
        for (int i = 0; i < 10; i++) {
            val messageValue = "mes from java " + Integer.toString(i);
            val key = "id_" + Integer.toString(i);

            val record =
                    new ProducerRecord<String, String>(
                            topicName,
                            key,
                            messageValue
                    );

            log.info("Key: " + key);

            sendRecord(record, producer);
        }
        // send some data

//        producer.flush();
        producer.close();
    }

    void sendRecord(ProducerRecord<String, String> record, KafkaProducer<String, String> producer) throws ExecutionException, InterruptedException {
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                log.info("Reciaved new metadata: \n" +
                        "\tTopic: " + metadata.topic() + "\n" +
                        "\tPartiotion: " + metadata.partition() + "\n" +
                        "\tOffset: " + metadata.offset() + "\n" +
                        "\tTimestamp: " + metadata.timestamp() + "\n"
                );
            } else {
                log.error("Error while producing", exception);
            }
        }).get(); // block the .send() to make it synchronous - don't do this in production
    }


    private Properties getProps(String bootstrapServer) {
        val props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

}

package com.github.simplesteph.kafka.tutorial1.producers;

import lombok.val;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {
    Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class);
    private String bootstrapServer = "127.0.0.1:9092";

    public static void main(String[] args) {
        ProducerDemoWithCallback obj = new ProducerDemoWithCallback();
        obj.run();
    }

    void run() {
        // Create properties
        val props = getProps(bootstrapServer);

        // Create producer
        val producer = new KafkaProducer<String, String>(props);

        // create a producer record
        for (int i = 0; i < 10; i++) {
            val record =
                    new ProducerRecord<String, String>(
                            "first_topic",
                            "mes from java " + i
                    );

            sendRecord(record, producer);
        }
        // send some data

//        producer.flush();
        producer.close();
    }

    void sendRecord(ProducerRecord<String, String> record, KafkaProducer<String, String> producer) {
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
        });
    }


    private Properties getProps(String bootstrapServer) {
        val props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

}

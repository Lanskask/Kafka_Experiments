package com.github.simplesteph.kafka.tutorial1.consumers;

import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {

    Logger log = LoggerFactory.getLogger(ConsumerDemo.class);
    private String bootstrapServer = "127.0.0.1:9092";
    private String groupId = "my-forth-app";
    private String topic = "first_topic";

    public static void main(String[] args) {
        ConsumerDemo obj = new ConsumerDemo();
        obj.run();
    }

    void run() {
        // Create properties
        val props = getProps(bootstrapServer);

//        // Create producer
//        val producer = new KafkaProducer<String, String>(props);
//
//        val record =
//                new ProducerRecord<String, String>(
//                        "first_topic",
//                        "mes from java "
//                );
//
//        sendRecord(record, producer);
//        // send some data
//
////        producer.flush();
//        producer.close();

        // create consumer
        val consumer = new KafkaConsumer<String, String>(props);

        // subscribe consumer to our topic(s)
        consumer.subscribe(Collections.singleton(topic));
//        consumer.subscribe(Arrays.asList("first_topic", "second_topic"));

        // poll for new data
        while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                log.info("Key: " + record.key() + "\n" +
                        "\tValue: " + record.value() + "\n" +
                        "\tPartitions: " + record.partition() + "\n" +
                        "\tOffset: " + record.offset()
                );
            }
        }

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
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

}

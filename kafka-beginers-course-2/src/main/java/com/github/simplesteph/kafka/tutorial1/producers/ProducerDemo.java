package com.github.simplesteph.kafka.tutorial1.producers;

import lombok.val;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {
        val bootstrapServer = "127.0.0.1:9092";

        System.out.println("Hello World!");

        val props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create producer
        val producer = new KafkaProducer<String, String>(props);


        // create a producer record
        val topicName = "first_topic";
        val message = "------------";
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);

        // send some data
        producer.send(record);

//        producer.flush();
        producer.close();
    }

}

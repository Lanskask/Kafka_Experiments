package test.kafka;

import java.util.Properties;
import kafka.javaapi.producer.Producer;
//import org.apache.kafka.clients.producer.KafkaProducer;
import kafka.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
import kafka.producer.KeyedMessage;

public class SimpleProducer {
    private static Producer<Integer, String> producer;
    private final Properties props = new Properties();
    public SimpleProducer() {
        props.put("broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        producer = new Producer<Integer, String>(new ProducerConfig(props));
    }

    public static void main(String[] args) {
        SimpleProducer sp = new SimpleProducer();
        String topic = (String) args[0];
        String messageStr = (String) args[1];
        KeyedMessage<Integer, String> data = new KeyedMessage<Integer,
                String>(topic, messageStr);
        producer.send(data);
        producer.close();
    }

}

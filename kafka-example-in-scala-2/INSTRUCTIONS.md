# Instractions
https://github.com/smallnest/kafka-example-in-scala

## Run Producer
java -cp target/kafka_example-0.1.0-SNAPSHOT.jar com.colobu.kafka.ScalaProducerExample 10000 test_topic localhost:9092

## Run Consumer
java -cp target/kafka_example-0.1.0-SNAPSHOT.jar com.colobu.kafka.ScalaConsumerExample localhost:9092 group1 test_topic 10 0

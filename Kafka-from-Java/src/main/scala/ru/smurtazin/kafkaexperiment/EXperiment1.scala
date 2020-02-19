package ru.smurtazin.kafkaexperiment

import java.util.Properties
//import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
//import kafka.producer.ProducerConfig

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.Producer

object SimpleProducer extends App {
  private var producer = null

  val sp = new SimpleProducer
  val topic = args(0).asInstanceOf[String]
  val messageStr = args(1).asInstanceOf[String]
  val data = new Nothing(topic, messageStr)
  producer.send(data)
  producer.close
}

class SimpleProducer() {
  props.put("broker.list", "localhost:9092")
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  props.put("request.required.acks", "1")
  SimpleProducer.producer = new Nothing(new Nothing(props))
  final private val props = new Properties
}
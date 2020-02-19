

kafka-topics --zookeeper 127.0.0.1:2181 --list 

kafka-console-producer --broker-list localhost:9092,localhost:9093,localho9094 --topic tes



# =============
# =============
#  Kafka Connect 
kafka-topics --zookeeper 127.0.0.1:2181 --create --topic twitter_status_connect --partitions 3 --replication-factor 1
kafka-topics --zookeeper 127.0.0.1:2181 --create --topic twitter_deletes_connect --partitions 3 --replication-factor 1



# =============
# =============   Promo Code
BONUSKAFKA


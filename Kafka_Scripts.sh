bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 10 --topic test_topic
docker run --rm       -p 2181:2181 -p 3030:3030       -p 8081-8083:8081-8083       -p 9581-9585:9581-9585       -p 9092:9092       -e ADV_HOST=127.0.0.1       landoop/fast-data-dev:latest

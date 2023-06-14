# Portfolio App

This repo has 3 apps:
- REST Server with endpoints to get data on portfolios and the securities in the portfolios
- Kafka Producer sends out stock information from IEX to topic 'securities'
- Kafka Consumer consumes the stock data and sends it out to a websocket so that [this web app](https://github.com/andrepimenta10/portfolio-app) can listen to the information 

This is a simple demo app. Kafka streams are great for when there are multiple applications listening to events/messages. For example, other servers could be consuming the messages. 

To run locally with front-end:
- Download and start kafka as specified in https://kafka.apache.org/quickstart
    - tar -xzf kafka_2.13-3.4.0.tgz
    - cd kafka_2.13-3.4.0
    - bin/zookeeper-server-start.sh config/zookeeper.properties
    - bin/kafka-server-start.sh config/server.properties
- Run com.portfolio.websocket.WebsocketService with -Dserver.port=8081
- Run com.portfolio.application.PorfolioApplication with -Dserver.port=8080
- Run com.portfolio.kafkaProducer.KafkaProducerApp

package com.portfolio.kafkaProducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.application.clients.IEXClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {

        String bootstrapServers = "localhost:9092";
        String topic = "securities";

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootstrapServers);
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);
        ObjectMapper objectMapper = new ObjectMapper();

        while (true) {
            for (String symbol: Arrays.asList("AAPL", "MSFT", "AMZN", "TSLA", "NVDA", "GOOG", "WMT")) {
                String jsonString = objectMapper.writeValueAsString(IEXClient.getSecurityInfo(symbol));
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, jsonString);
                producer.send(record);
                if (symbol.equals("WMT")) {
                    Thread.sleep(1000);
                }
            }
        }
    }
}

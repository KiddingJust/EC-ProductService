package com.gaiga.productservice.messagequeue;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	//접속 정보
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		Map<String, Object> properties = new HashMap<>();
//		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.30.0.101:9092");
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.30.0.104:9092,172.30.0.105:9096,172.30.0.106:9097");
//		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092,127.0.0.1:9096,127.0.0.1:9097");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroupId");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		
		return new DefaultKafkaConsumerFactory<>(properties);
	}
	
	//리스너
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory
			= new ConcurrentKafkaListenerContainerFactory<>();
		//위에서 등록한 접속정보 등록
		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
		
		return kafkaListenerContainerFactory;
	}
}

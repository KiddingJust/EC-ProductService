package com.gaiga.productservice.messagequeue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaiga.productservice.repository.ProductEntity;
import com.gaiga.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
	ProductRepository repository;
	
	@Autowired
	public KafkaConsumer(ProductRepository repository) {
		this.repository=repository;
	}
	
	//파라미터인 kafkaMessage ==> kafka topic에서 가져오는 것 
	@KafkaListener(topics = "ecommerce-product-topic")
	public void updateQty(String kafkaMessage) {
		log.info("Kafka Message: ===> " + kafkaMessage);
		
		//직렬화되어 전달온 메세지를 역직렬화해야 함. 
		Map<Object, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>(){});;
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//Object 형태로 가져오는 productId를 String으로 바꾸고 전달
		//수량 업데이트 작업 
		ProductEntity entity = repository.findByProductId((String)map.get("productId"));
		if (entity != null) {
			entity.setStock(entity.getStock()-(Integer)map.get("qty"));
			repository.save(entity);
		}
	}
}

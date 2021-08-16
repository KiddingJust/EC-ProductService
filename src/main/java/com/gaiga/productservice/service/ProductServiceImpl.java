package com.gaiga.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaiga.productservice.repository.ProductEntity;
import com.gaiga.productservice.repository.ProductRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public Iterable<ProductEntity> getAllProducts() {	
		return productRepository.findAll();
	}

}

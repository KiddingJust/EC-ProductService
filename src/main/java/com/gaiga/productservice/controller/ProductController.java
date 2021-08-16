package com.gaiga.productservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaiga.productservice.repository.ProductEntity;
import com.gaiga.productservice.service.ProductService;
import com.gaiga.productservice.vo.ResponseProduct;

@RestController
@RequestMapping("/product-service")
public class ProductController {

	Environment env;
	ProductService productService;
	
	@Autowired
	public ProductController(Environment env, ProductService productService) {
		this.env = env;
		this.productService = productService;
	}
	
	@GetMapping("/health_check")
	public String status() {
		return String.format("It's Working in Product SErvice on Port %s", 
						env.getProperty("local.server.port")
						);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ResponseProduct>> getProducts(){
		//위치상 Dto가 맞는듯한데, service에서 이렇게 해버린김에..!
		Iterable<ProductEntity> productList = productService.getAllProducts();
		
		List<ResponseProduct> result = new ArrayList<>();
		productList.forEach(v -> {
			result.add(new ModelMapper().map(v, ResponseProduct.class));
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}

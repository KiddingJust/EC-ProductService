package com.gaiga.productservice.service;

import com.gaiga.productservice.repository.ProductEntity;

public interface ProductService {
	Iterable<ProductEntity> getAllProducts();
}

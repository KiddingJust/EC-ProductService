package com.gaiga.productservice.repository;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>{

	ProductEntity findByProductId(String productId);
}

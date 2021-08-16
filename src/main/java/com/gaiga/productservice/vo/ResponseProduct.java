package com.gaiga.productservice.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
//null값 데이터는 반환하지 않도록! 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProduct {
	private String productId;
	private String productName;
	private Integer unitPrice;
	private Integer stock;
	private Date createdAt;
}

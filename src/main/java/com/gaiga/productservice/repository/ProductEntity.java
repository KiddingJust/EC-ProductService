package com.gaiga.productservice.repository;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
//데이터 직렬화를 위해 Serializable 상속. 이게 뭐지...?
public class ProductEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 120, unique = true)
	private String productId;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private Integer stock;
	
	@Column(nullable = false)
	private Integer unitPrice;
	
	@Column(nullable = false, updatable = false, insertable = false)
	//디폴트값 넣어주기. 현재 DATE 값 넣어주는 것. 
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	private Date createdAt;
}

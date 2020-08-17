package com.kevin.service.sharding.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//
//@Data
@Entity
@Table(name = "t_order")
public class OrderEntity {
	@Id
	private Long orderId;

	private Long userId;
	
	private Long gender;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

}

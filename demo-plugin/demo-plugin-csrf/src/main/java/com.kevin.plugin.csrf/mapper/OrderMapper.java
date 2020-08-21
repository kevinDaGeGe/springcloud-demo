package com.kevin.plugin.csrf.mapper;

import com.kevin.plugin.csrf.entity.OrderEntity;
import org.apache.ibatis.annotations.Insert;


public interface OrderMapper {
	@Insert("insert order_info values (null,#{orderName},#{orderDes})")
	public int addOrder(OrderEntity OrderEntity);
}

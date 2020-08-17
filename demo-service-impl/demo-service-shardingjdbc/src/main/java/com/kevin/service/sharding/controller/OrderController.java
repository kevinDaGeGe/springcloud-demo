package com.kevin.service.sharding.controller;

import com.kevin.service.sharding.entity.OrderEntity;
import com.kevin.service.sharding.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
	@Autowired
	private OrderMapper orderRepository;

	// 查询所有的订单信息
	@PostMapping("/getOrderAll")
	public List<OrderEntity> getOrderAll() {
		List<OrderEntity> listOrder = (List<OrderEntity>) orderRepository.findAll();
		System.out.println("总数：" + listOrder.size());
		return listOrder;
	}

	@PostMapping("/findIdByOrder")
	public Optional<OrderEntity> findIdByOrder(Long id) {
		return orderRepository.findById(id);
	}

	@PostMapping("/findIdByUserId")
	public List<OrderEntity> findIdByUserId(Long userId) {
		return orderRepository.findByUserId(userId);
	}

	// 使用in条件查询
	@GetMapping("/inOrder")
	public List<OrderEntity> inOrder() {
		List<String> ids = new ArrayList<>();
		ids.add("2");
		ids.add("3");
		ids.add("4");
		ids.add("5");
		return orderRepository.findExpiredOrderState(ids);

	}

	// 增加
	@PostMapping("/inserOrder")
	public String inserOrder(OrderEntity orderEntity) {
		for (int i = 0; i < 4; i++) {
			OrderEntity order = new OrderEntity();
			order.setOrderId((long) i);
			order.setUserId((long) i);
			order.setGender((long) (i+1));
			// 根据userid进行分片存放
			orderRepository.save(order);
		}
		return "success";
	}

}

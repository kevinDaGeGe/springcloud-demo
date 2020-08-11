package com.kevin.util.es.kevin.controller;

import com.kevin.util.es.kevin.entity.UserEntity;
import com.kevin.util.es.kevin.repository.UserReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class EsController {

	@Autowired
	private UserReposiory userReposiory;

	@RequestMapping("/addUser")
	public UserEntity addUser(@RequestBody UserEntity user) {
		return userReposiory.save(user);
	}

	@RequestMapping("/findUser")
	public Optional<UserEntity> findUser(String id) {
		return userReposiory.findById(id);
	}

}

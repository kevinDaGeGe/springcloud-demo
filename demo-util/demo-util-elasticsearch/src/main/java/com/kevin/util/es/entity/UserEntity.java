package com.kevin.util.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "kevin", type = "user")
@Data
public class UserEntity {
	@Id
	private String id;
	private String name;
	private int gender;
	private int age;
}

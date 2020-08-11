package com.kevin.service.mycat.mapper;

import com.kevin.service.api.mycat.BO.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {
	@Select("SELECT * FROM  user_info")
	public List<UserEntity> findUser();

	@Select("SELECT * FROM  user_info where userName=#{userName}")
	public UserEntity findUserByName(@Param("userName")String userName);

	@Select("insert into user_info values (#{userName}); ")
	public List<UserEntity> insertUser(@Param("userName") String userName);
}

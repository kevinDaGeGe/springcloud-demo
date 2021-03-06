package com.kevin.service.sharding.mapper;

import com.kevin.service.api.sharding.BO.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {
	@Select("SELECT * FROM  user_info ")
	public List<UserEntity> findUser();

	@Insert("insert into user_info values (#{userName}); ")
	public int insertUser(@Param("userName") String userName);
}

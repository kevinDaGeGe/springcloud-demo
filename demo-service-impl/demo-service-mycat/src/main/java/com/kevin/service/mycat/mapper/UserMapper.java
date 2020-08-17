package com.kevin.service.mycat.mapper;

import com.kevin.service.api.mycat.BO.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {
	@Select("SELECT * FROM  user_info_mycat")
	public List<UserEntity> findUser();

	@Select("SELECT * FROM  user_info_mycat where userName=#{userName}")
	public UserEntity findUserByName(@Param("userName")String userName);

	@Select("insert into user_info_mycat(id,userName) values (${id},#{userName}); ")
	public List<UserEntity> insertUser(@Param("id") int id,@Param("userName") String userName);
}

package com.kevin.plugin.xss.mapper;

import com.kevin.plugin.xss.entity.UserEntity;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {

	@Select(" SELECT  * FROM user_info where userName=#{userName} and password=#{password}")
	public UserEntity login(UserEntity userEntity);

}

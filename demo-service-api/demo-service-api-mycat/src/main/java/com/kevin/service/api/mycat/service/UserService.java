package com.kevin.service.api.mycat.service;

import com.kevin.service.api.mycat.BO.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @author: yu.han
 * @date: 2020/8/6 17:12
 */

public interface UserService {

    @RequestMapping("/findUser")
    public List<UserEntity> findUser();

    @RequestMapping("/findUserByName")
    public UserEntity findUserByName(String userName);

    @RequestMapping("/insertUser")
    public List<UserEntity> insertUser(String userName);
}

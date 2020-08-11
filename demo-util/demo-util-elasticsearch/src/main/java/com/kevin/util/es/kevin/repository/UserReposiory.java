package com.kevin.util.es.kevin.repository;

import com.kevin.util.es.kevin.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserReposiory extends CrudRepository<UserEntity, String> {

}

package com.kevin.util.es.repository;

import com.kevin.util.es.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserReposiory extends CrudRepository<UserEntity, String> {

}

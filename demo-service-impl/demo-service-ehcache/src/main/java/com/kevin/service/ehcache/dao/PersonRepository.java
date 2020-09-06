package com.kevin.service.ehcache.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kevin.service.ehcache.entity.Person;

/**
 * @author lz
 * @date 2018/12/27
 *
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {

	Person save(Person person);

	Optional<Person> findById(Integer id);

	void deleteById(Integer id);
}

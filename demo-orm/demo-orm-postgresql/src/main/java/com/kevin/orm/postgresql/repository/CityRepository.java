package com.kevin.orm.postgresql.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevin.orm.postgresql.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
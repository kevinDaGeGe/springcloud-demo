package com.kevin.orm.postgresql.service;
import java.util.List;

import com.kevin.orm.postgresql.entity.City;

public interface ICityService {

    List<City> findAll();
}
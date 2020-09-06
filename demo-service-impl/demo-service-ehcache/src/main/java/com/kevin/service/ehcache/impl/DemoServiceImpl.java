package com.kevin.service.ehcache.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kevin.service.ehcache.dao.PersonRepository;
import com.kevin.service.ehcache.entity.Person;
import com.kevin.service.ehcache.service.DemoService;

/**
 * @author lz
 * @date 2018/12/27
 */
@Service
@CacheConfig(cacheNames = {"myCache"})
public class DemoServiceImpl implements DemoService {
    @Autowired
    private PersonRepository personRepository;
    /**
     * 注意：如果没有指定key，则方法参数作为key保存到缓存中
     */
    /**
     * @param person
     * @return
     * @CachePut缓存新增的或更新的数据到缓存，其中缓存的名称为people，数据的key是person的id
     */
    @CachePut(key = "#person.id")
    @CacheEvict
    @Override
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id，key为：" + p.getId() + "数据做了缓存");
        return p;
    }

    /**
     * @param id
     * @CacheEvict从缓存people中删除key为id的数据
     */
    @CacheEvict
    @Override
    public void remove(Integer id) {
        System.out.println("删除了id，key为" + id + "的数据缓存");
        personRepository.deleteById(id);
    }

    /**
     * @param person
     * @return
     * @Cacheable缓存key为person的id数据到缓存people中
     */
    @Cacheable(key = "#person.id")
    @Override
    public Person findOne(Person person) {
        Optional<Person> p = personRepository.findById(person.getId());
        System.out.println("为id，key为：" + p.orElse(null) + "数据做了缓存");
        return p.orElse(null);
    }
}

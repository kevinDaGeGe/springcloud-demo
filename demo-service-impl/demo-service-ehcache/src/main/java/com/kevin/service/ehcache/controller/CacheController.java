package com.kevin.service.ehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevin.service.ehcache.entity.Person;
import com.kevin.service.ehcache.service.DemoService;

/**
 * @author lz
 * @date 2018/12/27
 */
@RestController
@RequestMapping("cache")
public class CacheController {
	@Autowired
    private DemoService demoService;

    public CacheController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping("/putPerson")
    public Person put(Person person) {
        return demoService.save(person);
    }

    @RequestMapping("/evict")
    public String evict(Integer id) {
        demoService.remove(id);
        return "ok";
    }

    @RequestMapping("/cacheable")
    public Person cacheable(Person person) {
        return demoService.findOne(person);
    }
    @RequestMapping("/put")
    public Person put() {
        Person person = new Person();
        person.setAddress("address");
        person.setName("name");
        person.setAge(1);
		return demoService.save(person );
    }
    @RequestMapping("/get")
    public Person get(int id) {
        Person person = new Person();
        person.setId(id);
		return demoService.findOne(person );
    }
    @RequestMapping("/save")
    public Person save(int id) {
        Person person = demoService.findOne(new Person(id));
        person.setAge(person.getAge()+1);
		return demoService.save(person );
    }
}

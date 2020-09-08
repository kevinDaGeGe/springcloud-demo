package com.kevin.orm.postgresql.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kevin.orm.postgresql.entity.City;
import com.kevin.orm.postgresql.service.ICityService;

@Controller
public class MyController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/showCities")
    public String findCities(Model model) {

        var cities = (List<City>) cityService.findAll();

        model.addAttribute("cities", cities);

        return "showCities";
    }
}
package com.demo.controller;


import com.demo.model.City;
import com.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Controller
@RequestMapping({"/api/v1"})
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "/city/{id}")
    public @ResponseBody
    List<City> getCityById(
            @PathVariable UUID id
    ){
        List<City> cityId = cityService.findById(id);
        return cityId;
    }


}

package com.company.employeepayrollapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class Controller {

    @GetMapping
    public String getRequest(){
        return "Employee Table";
    }
}

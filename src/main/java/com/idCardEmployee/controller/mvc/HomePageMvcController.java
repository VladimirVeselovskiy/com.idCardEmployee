package com.idCardEmployee.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/id-card-employee")
public class HomePageMvcController {

    @RequestMapping
    public String homePage(){

        return "home_page";
    }
}

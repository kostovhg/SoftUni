package com.softuni.javascriptajax.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {


    @GetMapping("/")
    public ModelAndView getIndex() {
        return super.view("index");
    }


}

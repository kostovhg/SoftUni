package com.softuni.javascriptajax.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController extends BaseController {

    @GetMapping("map")
    public ModelAndView getIndex() {
        return super.view("map");
    }
}

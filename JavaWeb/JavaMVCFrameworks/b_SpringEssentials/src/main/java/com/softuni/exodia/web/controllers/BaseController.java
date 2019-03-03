package com.softuni.exodia.web.controllers;

import com.softuni.exodia.services.GenericService;
import com.softuni.exodia.utils.MapperUtil;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController<T extends GenericService> {

    final MapperUtil mapper;
    T service;

    public BaseController(MapperUtil mapper) {
        this.mapper = mapper;
    }

    public BaseController(MapperUtil mapper, T service){
        this(mapper);
        this.service = service;
    }


    ModelAndView getView(ModelAndView modelAndView, String viewName) {
        modelAndView.setViewName(viewName);

        return modelAndView;
    }

    ModelAndView getView(ModelAndView modelAndView, String viewName, Object model) {
        modelAndView.setViewName(viewName);
        modelAndView.addObject("model", model);

        return modelAndView;
    }

    ModelAndView redirect(ModelAndView modelAndView){
        return this.redirect(modelAndView, "");
    }

    ModelAndView redirect(ModelAndView modelAndView, String viewName) {
        modelAndView.setViewName(String.format("redirect:/%s", viewName));
        return modelAndView;
    }

}
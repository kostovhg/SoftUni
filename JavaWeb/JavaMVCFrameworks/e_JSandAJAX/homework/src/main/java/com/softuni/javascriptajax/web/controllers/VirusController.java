package com.softuni.javascriptajax.web.controllers;

import com.softuni.javascriptajax.domain.models.binding.VirusCreateBindingModel;
import com.softuni.javascriptajax.domain.models.binding.VirusEditBindingModel;
import com.softuni.javascriptajax.domain.models.service.CapitalServiceModel;
import com.softuni.javascriptajax.domain.models.service.VirusServiceModel;
import com.softuni.javascriptajax.services.CapitalService;
import com.softuni.javascriptajax.services.VirusService;
import com.softuni.javascriptajax.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;
    private VirusService virusService;
    private MapperUtil mapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, MapperUtil mapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public ModelAndView add(
            ModelAndView modelAndView,
            @ModelAttribute(name = "bindingModel")
                    VirusCreateBindingModel bindingModel
    ) {
        List<CapitalServiceModel> capitals = this.capitalService.findAll();
        modelAndView.addObject("capitals", capitals);
        modelAndView.addObject("bindingModel", bindingModel);
        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(
            @Valid @ModelAttribute(name = "bindingModel")
                    VirusCreateBindingModel bindingModel,
            BindingResult bindingResult,
            ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", bindingModel);
            List<CapitalServiceModel> capitals = this.capitalService.findAll();
            modelAndView.addObject("capitals", capitals);
            return super.view("add-virus", modelAndView);
        } else {
            this.virusService.add(bindingModel);
            return super.redirect("/");
        }
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable final String id) {
        if (this.virusService.delete(id)) {
            return super.redirect("/");
        } else {
            return super.redirect("/show");
        }
    }


    @GetMapping("/edit/{id}")
    public ModelAndView getEdit(
            @PathVariable final String id,
            ModelAndView modelAndView,
            @ModelAttribute(name = "bindingModel") VirusEditBindingModel bindingModel
    ) {

        Map<CapitalServiceModel, Boolean> infectedCapitals = new LinkedHashMap<>();
        VirusServiceModel virus = this.virusService.findById(id);
        List<Integer> capitalsIds = virus.getCapitals().stream().map(CapitalServiceModel::getId).collect(Collectors.toList());
        List<CapitalServiceModel> capitals = this.capitalService.findAll();
        for (CapitalServiceModel capital : capitals) {
            infectedCapitals.put(capital, capitalsIds.contains(capital.getId()));
        }
        modelAndView.addObject("infectedCapitals", infectedCapitals);
        bindingModel = this.mapper.map(virus, VirusEditBindingModel.class);
        modelAndView.addObject("bindingModel", bindingModel);
        return super.view("edit-virus", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView postEdit(
            @PathVariable final String id,
            ModelAndView modelAndView,
            @ModelAttribute(name = "bindingModel") VirusEditBindingModel bindingModel) {
//        TODO: modify the record
        return super.redirect("show");
    }
}

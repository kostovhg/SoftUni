package com.softuni.javascriptajax.web.controllers;

import com.softuni.javascriptajax.domain.models.service.CapitalServiceModel;
import com.softuni.javascriptajax.domain.models.view.VirusListModel;
import com.softuni.javascriptajax.services.CapitalService;
import com.softuni.javascriptajax.services.VirusService;
import com.softuni.javascriptajax.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("show")
public class ShowController extends BaseController {


    private MapperUtil mapper;
    private VirusService virusService;
    private CapitalService capitalService;

    @Autowired
    public ShowController(MapperUtil mapper, VirusService virusService, CapitalService capitalService) {
        this.mapper = mapper;
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @GetMapping("")
    public ModelAndView allViruses(
            ModelAndView modelAndView) {
        return super.view("show", modelAndView);
    }

    @GetMapping("viruses")
    @ResponseBody
    public ModelAndView getAllViruses(ModelAndView modelAndView) {
        List<VirusListModel> viruses = this.mapper.map(this.virusService.findAll(), VirusListModel.class);
        modelAndView.addObject("viruses", viruses);
        return super.view("fragments/all-viruses", modelAndView);
    }

    @GetMapping("capitals")
    @ResponseBody
    public ModelAndView getAllCapitals(ModelAndView modelAndView) {
        List<CapitalServiceModel> capitals = this.capitalService.findAll();
        modelAndView.addObject("capitals", capitals);
        return super.view("fragments/all-capitals", modelAndView);
    }
}

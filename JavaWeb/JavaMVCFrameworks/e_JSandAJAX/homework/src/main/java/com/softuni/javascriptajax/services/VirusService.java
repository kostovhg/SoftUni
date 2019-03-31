package com.softuni.javascriptajax.services;

import com.softuni.javascriptajax.domain.models.binding.VirusCreateBindingModel;
import com.softuni.javascriptajax.domain.models.service.VirusServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VirusService {

    void add(VirusCreateBindingModel bindingModel);

    List<VirusServiceModel> findAll();

    boolean delete(String id);

    VirusServiceModel findById(String id);

    List<Integer> getInfectedCapitalsIds(String id);

}

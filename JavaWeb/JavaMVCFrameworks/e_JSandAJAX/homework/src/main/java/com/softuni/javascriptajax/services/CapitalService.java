package com.softuni.javascriptajax.services;

import com.softuni.javascriptajax.domain.models.service.CapitalServiceModel;

import java.util.List;

public interface CapitalService {
    List<CapitalServiceModel> findAll();
}

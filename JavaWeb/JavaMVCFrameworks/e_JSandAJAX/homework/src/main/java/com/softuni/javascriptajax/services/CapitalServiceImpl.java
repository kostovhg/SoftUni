package com.softuni.javascriptajax.services;

import com.softuni.javascriptajax.domain.models.service.CapitalServiceModel;
import com.softuni.javascriptajax.repositories.CapitalRepository;
import com.softuni.javascriptajax.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapitalServiceImpl implements CapitalService {


    private CapitalRepository capitalsRepository;
    private MapperUtil mapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalsRepository, MapperUtil mapper) {
        this.capitalsRepository = capitalsRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CapitalServiceModel> findAll() {
        return this.mapper.map(this.capitalsRepository.findAll(), CapitalServiceModel.class);
    }
}

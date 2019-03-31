package com.softuni.javascriptajax.services;

import com.softuni.javascriptajax.domain.entities.Virus;
import com.softuni.javascriptajax.domain.models.binding.VirusCreateBindingModel;
import com.softuni.javascriptajax.domain.models.service.CapitalServiceModel;
import com.softuni.javascriptajax.domain.models.service.VirusServiceModel;
import com.softuni.javascriptajax.repositories.VirusRepository;
import com.softuni.javascriptajax.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private MapperUtil mapper;
    private VirusRepository virusRepository;

    @Autowired
    public VirusServiceImpl(MapperUtil mapper, VirusRepository virusRepository) {
        this.mapper = mapper;
        this.virusRepository = virusRepository;
    }

    @Override
    public void add(VirusCreateBindingModel bindingModel) {
        this.virusRepository.saveAndFlush(this.mapper.map(bindingModel, Virus.class));
    }

    @Override
    public List<VirusServiceModel> findAll() {
        return this.mapper.map(this.virusRepository.findAll(), VirusServiceModel.class);
    }

    @Override
    public boolean delete(String id) {
        try {
            int intId = Integer.parseInt(id);
            this.virusRepository.deleteById(intId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public VirusServiceModel findById(String id) {
        return this.mapper.map(this.virusRepository.findById(Integer.parseInt(id)).orElse(null), VirusServiceModel.class);
    }

    @Override
    public List<Integer> getInfectedCapitalsIds(String id) {
        VirusServiceModel theVirus = this.mapper.map(this.virusRepository.findById(Integer.parseInt(id)).orElse(null), VirusServiceModel.class);
        if (theVirus != null){
            return theVirus.getCapitals()
                    .stream()
                    .map(CapitalServiceModel::getId)
                    .collect(Collectors.toList());
        }

        return null;
    }
}

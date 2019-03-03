package com.softuni.exodia.services;

import com.softuni.exodia.utils.MapperUtil;

import java.util.List;

public abstract class BaseService<ServiceModel> implements GenericService<ServiceModel> {

    final MapperUtil mapper;

    protected BaseService(MapperUtil mapperUtil) {
        this.mapper = mapperUtil;
    }

    @Override
    public abstract ServiceModel create(ServiceModel serviceModel);

    @Override
    public List<ServiceModel> findAll(){
        return null;
    }

}

package com.softuni.exodia.services;

import java.util.List;

public interface GenericService<ServiceModel> {

    ServiceModel create(ServiceModel createServiceModel);

    ServiceModel findBy(String username, String value);

    List<ServiceModel> findAll();
}

package com.softuni.javascriptajax.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {

    private ModelMapper mapper;

    public MapperUtil(){
        this.mapper = new ModelMapper();
    }

    public <S, D> D map(S source, Class<D> destinationClass){
        return this.mapper.map(source, destinationClass);
    }

    public <S, D> List<D> map (List<S> sourceList, Class<D> destinationClass){
        return sourceList.stream()
                .map(s -> this.mapper.map(s, destinationClass))
                .collect(Collectors.toList());
    }
}

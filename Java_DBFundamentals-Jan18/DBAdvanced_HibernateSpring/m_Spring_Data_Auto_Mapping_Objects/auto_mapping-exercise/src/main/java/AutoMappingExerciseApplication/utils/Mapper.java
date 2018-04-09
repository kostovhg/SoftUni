package AutoMappingExerciseApplication.utils;

import org.modelmapper.ModelMapper;

public class Mapper {

    private static ModelMapper modelMapper;

    public Mapper() {
    }

    public static ModelMapper getInstance(){
        if(modelMapper == null){
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }
}

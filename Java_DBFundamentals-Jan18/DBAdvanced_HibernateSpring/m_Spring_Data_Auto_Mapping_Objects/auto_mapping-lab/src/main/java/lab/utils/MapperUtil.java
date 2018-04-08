package lab.utils;

import lab.dto.ElderEmployeeDTO;
import lab.dto.EmployeeDTO;
import lab.dto.ManagerDTO;
import lab.models.entities.Employee;
import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

public class MapperUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    public MapperUtil() {
    }

    // Properties with different naming wont be mapped (for example initials in DTO)
    public static EmployeeDTO convertEmployee(Employee employee){
        // map straight from employee (src) to EmployeeDTO.class (dest)
        return modelMapper.map(employee, EmployeeDTO.class);

    }

    public static Employee convertEmployeeDTO(EmployeeDTO employeeDTO){
        // map straight from employeeDTO (src) to Employee.class (dest)
        // return DTO
        return modelMapper.map(employeeDTO, Employee.class);

    }

    public static ManagerDTO convertEmployeeToManagerDTO(Employee employee){
        return modelMapper.map(employee, ManagerDTO.class);
    }

    // The previous two methods generalized as:
    public static <S, D> D convert(S source, Class<D> destClass){
        return modelMapper.map(source, destClass);
    }

    public static EmployeeDTO convertEmployeeToDTO(Employee employee){
        EmployeeDTO employeeDTO = null;

        ExpressionMap<Employee, EmployeeDTO> nameToInitialsMapper =
                m -> m.map(src ->
                                src.getFirstName().substring(0, 2) +
                                        ". " +
                                        src.getLastName().substring(0, 2) +
                                        ".",
                        EmployeeDTO::setInitials);

        ModelMapper mapper = new ModelMapper();

        return employeeDTO;

    }

    public static ModelMapper getModelMapper(){
        return modelMapper;
    }

    public static ElderEmployeeDTO convertEmplToElderEmployeeDTO(Employee employee){
        ElderEmployeeDTO result = new ElderEmployeeDTO();

        modelMapper.addMappings(new PropertyMap<Employee, ElderEmployeeDTO>() {
            @Override
            protected void configure() {
                using(new Converter<String, String>() {
                    @Override
                    public String convert(MappingContext<String, String> context) {
                        Employee src = (Employee) context.getParent().getSource();
                        return src.getFirstName() + " " + src.getLastName();
                    }
                }).map(source.getLastName()).setFullName("fullName");
               using(new Converter<Boolean, String>() {
                   @Override
                   public String convert(MappingContext<Boolean, String> context) {
                       Boolean manager = employee.getManager() == null;
                       String mName = manager ? "[no manager]" : employee.getManager().getLastName();
                       return mName;
                   }
               }).map(source.getManager()).setManagerLastName("[no manager]");
            }
        });

        result = modelMapper.map(employee, ElderEmployeeDTO.class);
        return result;
    }
}

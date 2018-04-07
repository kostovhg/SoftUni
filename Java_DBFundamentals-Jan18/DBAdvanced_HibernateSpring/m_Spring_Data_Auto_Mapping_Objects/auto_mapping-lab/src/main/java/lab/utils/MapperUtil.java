package lab.utils;

import lab.dto.EmployeeDTO;
import lab.dto.ManagerDTO;
import lab.models.entities.Employee;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;

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


}

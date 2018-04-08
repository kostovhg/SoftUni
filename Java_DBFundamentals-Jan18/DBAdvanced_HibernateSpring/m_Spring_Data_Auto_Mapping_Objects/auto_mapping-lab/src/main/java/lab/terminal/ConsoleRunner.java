package lab.terminal;

import lab.dto.ElderEmployeeDTO;
import lab.dto.EmployeeDTO;
import lab.dto.ManagerDTO;
import lab.models.entities.Address;
import lab.models.entities.City;
import lab.models.entities.Employee;
import lab.services.api.AddressService;
import lab.services.api.CityService;
import lab.services.api.EmployeeService;
import lab.utils.MapperUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.java2d.pipe.SpanShapeRenderer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static lab.utils.MapperUtil.*;
import static lab.utils.MapperUtil.convert;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static Random rnd = new Random();
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-mm-dd");

    private EmployeeService employeeService;
    private CityService cityService;
    private AddressService addressService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService, CityService cityService, AddressService addressService) {
        this.employeeService = employeeService;
        this.cityService = cityService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {

        Employee employee = null;

        if (this.employeeService.countAll() < 1) {
            // populate

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

            Address address = new Address(new City("Sofia"), "12 Tyntyqva");
            this.addressService.save(address);


            Employee aManager = new Employee("Stefcho", "Shefchov", address, sdf.parse("1998-10-12"), new BigDecimal(10358.80));
            Employee firstEmployee = new Employee("Ivan", "Ivanov", address, sdf.parse("1998-10-12"), new BigDecimal(1358.80));
            Employee secondEmployee = new Employee("Georgi", "Ivanov", address, sdf.parse("1967-05-30"), new BigDecimal(3218.00));
            Employee thirdEmployee = new Employee("Petar", "Mihailov", address, sdf.parse("1967-05-30"), new BigDecimal(3218.00));

            firstEmployee.setManager(aManager);
            aManager.getManagedEmployees().add(firstEmployee);
            secondEmployee.setManager(aManager);
            aManager.getManagedEmployees().add(secondEmployee);
            thirdEmployee.setManager(aManager);
            aManager.getManagedEmployees().add(thirdEmployee);
            this.employeeService.save(aManager);
            this.employeeService.save(firstEmployee);
            this.employeeService.save(secondEmployee);
            this.employeeService.save(thirdEmployee);
        }

        employee = this.employeeService.findById(1);

        // task 1 - convert from one to other with special method for output class
        System.out.println(" Map Employee with id 1 to EmployeeDTO");
        EmployeeDTO employeeDTO = convertEmployee(employee);
        System.out.println(employeeDTO);
        Employee convertedFromDTO = convertEmployeeDTO(employeeDTO);
        System.out.println(" Map EmployeeDTO to Employee");
        System.out.println(convertedFromDTO);

        // task 1 - convert from one to other with generalized method
        employeeDTO = convert(employee, EmployeeDTO.class);
        System.out.println(" Map Employee with id 1 to EmployeeDTO");
        System.out.println(employeeDTO);
        System.out.println(" Map EmployeeDTO to Employee");
        System.out.println(convert(employeeDTO, Employee.class));

        // task 2

        ManagerDTO manager = convertEmployeeToManagerDTO(employee);
        System.out.println(manager);

        System.out.println("test");

        // task 3

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<Employee> elders = this.employeeService.listAllByBirthDateBeforeOrderBySalaryDesc(sdf.parse("19800101"));
//        for (Employee elder : elders) {
//            System.out.println(convertEmplToElderEmployeeDTO(elder));
//        }

        System.out.println(convertEmplToElderEmployeeDTO(employee));

        ModelMapper mm = MapperUtil.getModelMapper();

        Collection<TypeMap<?, ?>> typeMaps = mm.getTypeMaps();
        for (TypeMap<?, ?> typeMap : typeMaps) {
            System.out.println(typeMap.getSourceType().getSimpleName() + " -> " + typeMap.getDestinationType().getSimpleName());
            List<Mapping> list = typeMap.getMappings();
            for (Mapping mapping : list) {
                System.out.println(mapping);
            }
        }

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        List<Employee> olderEmployees = employeeService.listAllByBirthDateBeforeOrderBySalaryDesc(sdf.parse("1990910101"));

//        for (Employee olderEmployee : olderEmployees) {
//            System.out.println(emplToElderDTO(olderEmployee));
//        }


    }

}

package lab.terminal;

import lab.dto.EmployeeDTO;
import lab.dto.ManagerDTO;
import lab.models.entities.Address;
import lab.models.entities.City;
import lab.models.entities.Employee;
import lab.services.api.AddressService;
import lab.services.api.CityService;
import lab.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Random;

import static lab.utils.MapperUtil.convertEmployee;
import static lab.utils.MapperUtil.convertEmployeeDTO;
import static lab.utils.MapperUtil.convertEmployeeToManagerDTO;

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


            Employee aManager = new Employee("Stefcho", "Shefchov", address, sdf.parse("1098-10-12"), new BigDecimal(10358.80));
            Employee firstEmployee = new Employee("Ivan", "Ivanov", address, sdf.parse("1098-10-12"), new BigDecimal(1358.80));
            Employee secondEmployee = new Employee("Georgi", "Ivanov", address, sdf.parse("1967-05-30"), new BigDecimal(3218.00));
            Employee thirdEmployee = new Employee("Petar", "Mihailov", address, sdf.parse("1967-05-30"), new BigDecimal(3218.00));


//            aManager.addManagedEmployee(firstEmployee);
//            aManager.addManagedEmployee(secondEmployee);
//            aManager.addManagedEmployee(thirdEmployee);
            firstEmployee.setManager(aManager);
            secondEmployee.setManager(aManager);
            thirdEmployee.setManager(aManager);
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
        System.out.println(" Map already mapped EmployeeDTO back to Employee");
        System.out.println(convertEmployeeDTO(employeeDTO));

        // task 1 - convert from one to other with generalized method - StackOverflowException!?!!
//        employeeDTO = convert(employee, EmployeeDTO.class);
//
//        System.out.println(employeeDTO);
//
//        System.out.println(convert(employeeDTO, Employee.class));

        // task 2

        ManagerDTO manager = convertEmployeeToManagerDTO(employee);
        System.out.println(manager);

        System.out.println("test");
    }



}

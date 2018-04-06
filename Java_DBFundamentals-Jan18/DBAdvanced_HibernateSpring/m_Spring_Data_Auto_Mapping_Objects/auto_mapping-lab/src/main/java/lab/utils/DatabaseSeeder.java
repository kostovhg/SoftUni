package lab.utils;

import lab.models.entities.Address;
import lab.models.entities.City;
import lab.models.entities.Employee;
import lab.services.api.AddressService;
import lab.services.api.CityService;
import lab.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseSeeder extends ResourceDatabasePopulator {

    private static final List<String> CITIES_LIST = new ArrayList<>(Arrays.asList(
            "Sofia", "Plovdiv", "Stara Zagora", "Ruse", "Bourgas", "Varna"));
    private static final List<String> FIRST_NAMES_LIST = new ArrayList<>(Arrays.asList(
            "Georgi", "Ivan", "Dimitar", "Petar", "Stamat", "Kristian"));
    private static final List<String> LAST_NAMES_LIST = new ArrayList<>(Arrays.asList(
            "Stoqnov", "Pavlov", "Ivanov", "Georgiev", "Vaklinov", "Papazov"));
    private static final List<String> STREETS_LIST = new ArrayList<>(Arrays.asList(
            "15 Tyntyava", "123 Tsarigradsko shose", "34 Nezabravka", "13 Ruen", "68 Suhidol", "93 Pazardzhishko shose"));

    private static Random rnd = new Random();
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-mm-dd");

    private EmployeeService employeeService;
    private CityService cityService;
    private AddressService addressService;

    @Autowired
    public DatabaseSeeder(EmployeeService employeeService, CityService cityService, AddressService addressService) {
        this.employeeService = employeeService;
        this.cityService = cityService;
        this.addressService = addressService;
    }

    private void seedDatabase() throws ParseException {

        seedCities();
        seedAddresses();

        List<Employee> employeeSet = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            employeeSet.add(new Employee(
                    getRandomFrom(FIRST_NAMES_LIST),
                    getRandomFrom(LAST_NAMES_LIST),
                    getRandomFrom(addressService.findAll()),
                    getRandomDateBetweenYears(1959, 2005),
                    getRandomSalary()));
        }

        this.employeeService.saveAll(employeeSet);
    }

    private void seedAddresses() {
        List<Address> addressesList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            City city = this.cityService.findById((long) rnd.nextInt(CITIES_LIST.size()));
            String street = getRandomFrom(STREETS_LIST);
            Address addr = new Address(city, street);
            addressesList.add(addr);
        }
        this.addressService.saveAll(addressesList);
    }

    private void seedCities() {
        List<City> cities = CITIES_LIST.stream().map(City::new).collect(Collectors.toList());
        this.cityService.saveAll(cities);
    }

    private BigDecimal getRandomSalary() {
        BigDecimal rndSalary = new BigDecimal(510);
        return rndSalary.add(BigDecimal.valueOf(rnd.nextInt(50000)));
    }

    private Date getRandomDateBetweenYears(int startYear, int endYear) throws ParseException {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(startYear, endYear);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return FORMATTER.parse(String.format("%d-%02d-02d",
                gc.get(gc.YEAR), (gc.get(gc.MONTH) + 1), gc.get(gc.DAY_OF_MONTH)));

    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private <E> E getRandomFrom(List<E> theList) {
        return theList.get(rnd.nextInt(theList.size()));
    }

}

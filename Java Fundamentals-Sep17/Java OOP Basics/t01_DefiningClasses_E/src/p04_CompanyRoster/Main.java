package p04_CompanyRoster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, Department> depSet = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        String[] input;
        for (int i = 0; i < count; i++) {
            input = reader.readLine().split("\\s+");
            String name = input[0];
            BigDecimal salary = new BigDecimal(input[1]);
            String position = input[2];
            String department = input[3];

            if (!depSet.containsKey(department)) {
                depSet.put(department, new Department(department));
            }
            /*depSet.get(department)
                    .addEmploy(new Employee(name, salary, position, department ));*/
            depSet.get(department).addEmploy(new Employee(input));
        }

        System.out.print("Highest Average Salary: ");
        Collections.max(depSet.values(),
                Comparator.comparing(Department::getAverageSalary))
                .printDepartament();
    }
}

class Person {
    private String name;
    private int age = -1;

    String getName() { return name; }

    void setName(String name) { this.name = name; }

    int getAge() { return age; }

    void setAge(int age) { this.age = age; }
}

class Employee extends Person {
    private double salary;
    private String position;
    private String department;
    private String email = "n/a";

    double getSalary() { return salary; }

    void printEmployee() {
        System.out.println(String.format(
                "%s %.2f %s %d", getName(), salary, email, getAge()
        ));
    }

    Employee(String[] tokens) {
        this.setName(tokens[0]);
        this.salary = Double.parseDouble(tokens[1]);
        this.position = tokens[2];
        this.department = tokens[3];
        if(tokens.length < 5) return;
        if (tokens[4].contains("@")) this.email = tokens[4];
        else this.setAge(Integer.valueOf(tokens[4]));
        if(tokens.length < 6) return;
        if (tokens[5].contains("@")) this.email = tokens[5];
        else this.setAge(Integer.valueOf(tokens[5]));
    }

    Employee(String name, double salary, String position, String department) {
        this.setName(name);
        this.salary = (salary);
        this.position = position;
        this.department = department;
    }

    Employee(String name, double salary, String position, String department, String email, int age) {
        this(name, salary, position, department);
        this.email = email;
        this.setAge(age);
    }
}

class Department {
    private String depName;
    private List<Employee> employees;

    Department(String name) {
        this.depName = name;
        this.employees = new LinkedList<>();
    }

    void addEmploy(Employee employee) {
        this.employees.add(employee);
    }

    void printDepartament() {
        System.out.println(this.depName);
        this.employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(Employee::printEmployee);
    }

    double getAverageSalary() {
        return employees.stream().mapToDouble(x -> x.getSalary()).average().getAsDouble();
    }
}

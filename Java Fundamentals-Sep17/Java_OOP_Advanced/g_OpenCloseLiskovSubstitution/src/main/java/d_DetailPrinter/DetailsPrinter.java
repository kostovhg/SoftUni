package d_DetailPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailsPrinter {

    private Iterable<Employee> employees;

    public DetailsPrinter(Iterable<Employee> employees) {
        this.employees = employees;
    }

    public void printDetails() {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Pesho"));
        employees.add(new Manager("Gosho", Arrays.asList("doc1.txt", "doc2.txt")));
        DetailsPrinter detailsPrinter = new DetailsPrinter(employees);

        detailsPrinter.printDetails();
    }
}

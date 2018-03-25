import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class i_IncreaseSalaries {

    public static void main(String[] args) {

        List<String> departmentsWithRise = new ArrayList<>();
        // In the problem description example result in the output names from "Engineering" department are missing
        // probably typo in the query.
        departmentsWithRise.add("'Engineering'");
        departmentsWithRise.add("'Tool Design'");
        departmentsWithRise.add("'Marketing'");
        departmentsWithRise.add("'Information Service'");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employees = em.createQuery(
                "SELECT e FROM Employee AS e WHERE e.department.name IN (" + String.join(", ", departmentsWithRise) +")", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.24)));
        }

        StringBuilder sb = new StringBuilder();
        employees.stream()
                // The result are sorted by id in the example output
                .sorted(Comparator.comparing(Employee::getId))
                .forEach(e -> sb.append(String.format("%s %s ($%.2f)%n",
                        e.getFirstName(), e.getLastName(), e.getSalary())));
        System.out.println(sb.toString().trim());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}

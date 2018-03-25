import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class k_FindEmployeesByFirstName {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine() + "%";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        Query findEmployeesQuery = em.createQuery(
                "SELECT e FROM Employee AS e WHERE e.firstName LIKE :pattern");
        findEmployeesQuery.setParameter("pattern", pattern);

        List<Employee> employees = findEmployeesQuery.getResultList();

        employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(),
                e.getLastName(),
                e.getDepartment().getName(),
                e.getSalary()));

        em.close();
        emf.close();
    }
}

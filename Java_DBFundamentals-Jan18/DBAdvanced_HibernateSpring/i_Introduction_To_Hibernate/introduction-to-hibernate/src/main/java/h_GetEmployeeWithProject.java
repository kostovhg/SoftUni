import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class h_GetEmployeeWithProject {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Integer employeeId = Integer.valueOf(reader.readLine());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        //em.getTransaction().begin();
        Employee employee = em.createQuery("SELECT e FROM Employee AS e WHERE id = :id", Employee.class)
                .setParameter("id", employeeId)
                .getSingleResult();

        /* Was returning error "Cannot convert value '<some date>' from column 5 to TIMESTAMP
        until added &amp;useFastDateParsing=false to the url in persistence.xml
         and projects set for the employee was empty */
        System.out.printf("%s %s -%s",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("%n\t%s", p.getName()));

        //em.getTransaction().commit();
        em.close();
        emf.close();
    }
}

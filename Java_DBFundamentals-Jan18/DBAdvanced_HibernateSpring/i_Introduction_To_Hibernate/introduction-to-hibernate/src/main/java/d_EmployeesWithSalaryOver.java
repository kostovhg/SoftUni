import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class d_EmployeesWithSalaryOver {
    public static void main(String[] args) {

        /* Criteria value */
        String strValue = "50000";
        /* In case we know the type of the field on which base we create criteria */
        BigDecimal value = new BigDecimal(strValue);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        /* Extract all of the entities with higher salaries */
        List<Employee> results = null;

        results = em.createQuery("FROM Employee " +
                "WHERE salary > " +
                strValue).getResultList();

        /* Extracting only the names */
        List<String> names =
                null;
        names = em.createQuery("SELECT firstName FROM Employee WHERE salary > :salary")
                .setParameter("salary", value)
                .getResultList();
        names.forEach(System.out::println);

        results.forEach(e -> System.out.println(e.getFirstName()));

        em.close();
        emf.close();
    }
}


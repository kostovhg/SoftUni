import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class l_EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        @SuppressWarnings("unchecked")
        List<Object[]> results = em.createQuery("SELECT e.department.name, MAX(e.salary) AS max_salary " +
                " FROM Employee AS e " +
                "GROUP BY e.department " +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000 " +
                "ORDER BY e.department.id").getResultList();

        results.forEach(d -> System.out.printf("%s - %.2f\n", d[0], d[1]));

        em.close();
        emf.close();
    }
}

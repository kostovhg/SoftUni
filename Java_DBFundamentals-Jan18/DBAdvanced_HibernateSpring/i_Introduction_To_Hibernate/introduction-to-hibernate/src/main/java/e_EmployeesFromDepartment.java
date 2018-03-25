import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class e_EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        String departmentName = "Research and Development";

        /* Get only interesting fields */
        @SuppressWarnings("unchecked")
        List<Object[]> result = em.createQuery("SELECT " +
                "e.firstName, e.lastName, e.department.name, salary " +
                "FROM Employee AS e " +
                "WHERE e.department.name = \'" +
                departmentName +
                "\' ORDER BY salary ASC, id ASC")
                .getResultList();

        for (Object[] objects : result) {
            System.out.printf("%s %s from %s - $%.2f%n",
                    objects[0], objects[1], objects[2], objects[3]);

        }

        em.close();
        emf.close();
    }
}

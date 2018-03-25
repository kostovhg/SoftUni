import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class g_AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        @SuppressWarnings("unchecked")
        List<Address> addresses = em
                .createQuery("SELECT DISTINCT a FROM Address AS a ORDER BY a.employees.size DESC, a.town.id")
                .setMaxResults(10)
                .getResultList();

        StringBuilder sb = new StringBuilder();
        for (Address address : addresses) {
            sb.append(String.format(
                    "%n%s, %s - %d employees",
                    address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size()
            ));
        }
        System.out.println(sb.toString().trim());
    }
}

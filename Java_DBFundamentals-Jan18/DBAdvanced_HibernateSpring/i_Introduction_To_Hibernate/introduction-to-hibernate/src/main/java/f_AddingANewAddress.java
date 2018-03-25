import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class f_AddingANewAddress {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String lastName = reader.readLine();
        String newAddress = "Vitoshka 15";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        Employee employee = (Employee) em.createQuery("FROM Employee WHERE lastName LIKE ('" + lastName +"')").getSingleResult();

        Address addr = new Address();
        addr.setText(newAddress);
        employee.setAddress(addr);

        em.getTransaction().begin();

        em.persist(employee);
        em.persist(addr);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
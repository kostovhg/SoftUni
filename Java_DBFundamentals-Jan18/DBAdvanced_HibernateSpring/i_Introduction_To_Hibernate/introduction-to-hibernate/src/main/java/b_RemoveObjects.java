import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class b_RemoveObjects {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();


        List<Town> towns = em
                .createQuery("SELECT t FROM Town AS t")
                .getResultList();

        for (Town town : towns) {
            if (town.getName().length() > 5) {
                em.detach(town);
            }
        }

        em.getTransaction().begin();

        towns.stream().filter(em::contains).forEach(town -> {
            town.setName(town.getName().toLowerCase());
            em.persist(town);
        });

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
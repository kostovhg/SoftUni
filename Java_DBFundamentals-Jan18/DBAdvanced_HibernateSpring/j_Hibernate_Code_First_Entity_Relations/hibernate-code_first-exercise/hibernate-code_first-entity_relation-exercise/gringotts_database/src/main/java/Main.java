import entities.WizardDeposit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        WizardDeposit wd = new WizardDeposit();
        em.persist(wd);
        em.getTransaction().commit();
        em.close();
    }
}
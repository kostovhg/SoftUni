import entities.ingredients.BasicIngredient;
import entities.ingredients.Mint;
import entities.ingredients.Nettle;
import entities.ingredients.chemicalIngredients.AmmoniumChloride;
import entities.labels.BasicLabel;
import entities.shampoos.BasicShampoo;
import entities.shampoos.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory mf = Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager em = mf.createEntityManager();

        try {
            em.getTransaction().begin();


            BasicIngredient am = new AmmoniumChloride();
            em.persist(am);
            BasicIngredient mint = new Mint();
            em.persist(mint);
            BasicIngredient nettle = new Nettle();
            em.persist(nettle);

            BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "Contains mint and nettle");

            em.persist(label);

            BasicShampoo shampoo = new FreshNuke(label);

//            shampoo.getIngredients().add(mint);
//            shampoo.getIngredients().add(nettle);
//            shampoo.getIngredients().add(am);
            em.persist(shampoo);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            mf.close();
        }
    }
}

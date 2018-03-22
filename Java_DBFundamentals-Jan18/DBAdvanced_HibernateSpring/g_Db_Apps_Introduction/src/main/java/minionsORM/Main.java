package minionsORM;

import minionsORM.entities.Minion;
import minionsORM.entities.Town;
import minionsORM.orm.EntityManager;
import minionsORM.orm.EntityManagerBuilder;
import minionsORM.strategies.DropCreateStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

import static minionsORM.utilities.Constants.CONN_PROPERTIES;
import static minionsORM.utilities.Constants.DB_NAME;

public class Main {

    public static void main(String[] args) throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (String key : CONN_PROPERTIES.keySet()) {
            System.out.printf("Enter %s (default %s): ", key, CONN_PROPERTIES.get(key));
            String answer = reader.readLine();
            if (!answer.equals("")) {
                CONN_PROPERTIES.put(key, answer);
            }
        }

        System.out.printf("Add connection options in format <option>=<true/false> (default no): ");
        String options = reader.readLine();


        // Prepare builder
        EntityManagerBuilder builder = new EntityManagerBuilder();
        EntityManager em = builder.configureConnectionString()
                .setDriver("jdbc")
                .setAdapter("mysql")
                .setHost("localhost")
                .setPort(CONN_PROPERTIES.get("port"))
                .setUser(CONN_PROPERTIES.get("username"))
                .setPass(CONN_PROPERTIES.get("password"))
                .setOptions(options)
                .createConnection()
                .setDataSource(DB_NAME)
                .configureCreationType().set(DropCreateStrategy.class)
                .build();

        /*
        0. Create Tables (drop and create new)
         */

        List<Town> towns = new ArrayList<>();
        towns.add(new Town("Sofia", "Bulgaria"));
        towns.add(new Town("Berlin", "Germany"));

        for (Town town : towns) {
            em.persist(town);
        }

        List<Minion> minions = new ArrayList<>();
        minions.add(new Minion("Bob", 13, 1));
        minions.add(new Minion("Steward", 15, 1));


        /*
        1. Initial Setup
         */

    }

}
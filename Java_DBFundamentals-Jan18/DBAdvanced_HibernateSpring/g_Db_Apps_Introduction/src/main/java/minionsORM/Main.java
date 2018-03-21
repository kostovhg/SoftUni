package minionsORM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static minionsORM.Constants.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        /*
        0. Create Tables if not exist (table will be created from optional parameter passed to the URL
         */

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prstmt = conn.prepareStatement(SQL_CREATE_QUERY);
            prstmt.executeQuery();
            //prstmt = conn.prepareStatement()
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        1. Initial Setup
         */

    }

}
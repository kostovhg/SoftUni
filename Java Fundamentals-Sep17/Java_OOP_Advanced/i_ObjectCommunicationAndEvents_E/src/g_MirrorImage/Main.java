package g_MirrorImage;

import com.sun.corba.se.impl.orbutil.RepositoryIdStrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        WizardFactory factory = new WizardFactory();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split("\\s+");
        Wizard firstOne = factory.create(tokens[0], Integer.parseInt(tokens[1]));
        Repository.add(firstOne);

        String line = reader.readLine();
        while(!line.equals("END")) {
            tokens = line.split("\\s+");
            int currentId = Integer.parseInt(tokens[0]);
            String action = tokens[1];
            switch (action) {
                case "FIREBALL":
                    Repository.get(currentId).castFireball();
                    break;
                case "REFLECTION":
                    Repository.get(currentId).createReflection();
                  /*  Wizard currentWizard = Repository.get(currentId);
                    for (Wizard mirrorImage : currentWizard.getCopies()) {
                        Repository.add(mirrorImage);
                    }*/
                    break;
            }
            line = reader.readLine();
        }
    }
}

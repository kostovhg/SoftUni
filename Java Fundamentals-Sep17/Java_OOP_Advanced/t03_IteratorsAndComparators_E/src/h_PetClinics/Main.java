package h_PetClinics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, Pet> pets = new HashMap<>();
    private static Map<String, Clinic> clinics = new HashMap<>();

    public static void main(String[] args) throws IOException {

        int numberOfCommands = Integer.parseInt(reader.readLine());

        while (numberOfCommands-- > 0) {
            interpretCommand(reader.readLine());
        }
    }

    private static void interpretCommand(String s) {
        String[] commands = s.split("\\s+");
        switch (commands[0]) {
            case "Create":
                try {
                    switch (commands[1]) {
                        case "Pet":
                            pets.putIfAbsent(commands[2], new Pet(commands[2], commands[3], commands[4]));
                            break;
                        case "Clinic":
                            clinics.putIfAbsent(commands[2], new Clinic(commands[2], commands[3]));
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "Add":
                if (pets.containsKey(commands[1]) && clinics.containsKey(commands[2])) {
                    Clinic clinic = clinics.get(commands[2]);
                    Pet pet = pets.get(commands[1]);
                    System.out.println(clinic.addPet(pet));
                }
                break;
            case "Release":
                if (clinics.containsKey(commands[1])) {
                    System.out.println(clinics.get(commands[1]).releasePet());
                }
                break;
            case "HasEmptyRooms":
                if (clinics.containsKey(commands[1])) {
                    System.out.println(clinics.get(commands[1]).hasEmptyRoom());
                }
                break;
            case "Print":
                if (commands.length == 3 && clinics.containsKey(commands[1])) {
                    Pet currentPet = clinics.get(commands[1]).getPetByIndex(commands[2]);
                    System.out.println((currentPet == null) ? "Room empty": currentPet);
                } else if (clinics.containsKey(commands[1])) {
                    clinics.get(commands[1]).forEach(p -> {
                        if(p == null){
                            System.out.println("Room empty");
                        } else {
                            System.out.println(p);
                        }
                    });
                }
                break;
        }
    }
}

/*
creating a pet,
creating a clinic,
adding a pet to a clinic,
releasing a pet from a clinic,
printing information about a specific room in a clinic or
printing information about all rooms in a clinic.
 */

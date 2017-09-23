package t01_IntroToJava_E;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p12_VehiclePark {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*

         */

        List<String> vehicles = new ArrayList<String>(Arrays.asList(input.nextLine().split("\\s+")));
        int soldCount = 0;
        String[] request;
        while(!(request = input.nextLine().split("\\s"))[0].equals("End")){
            boolean sold = false;
            char vehicleType = request[0].toLowerCase().toCharArray()[0];
            String vehicleSeats = request[2];
            for (int i = 0; i < vehicles.size() && !sold; i++) {
                if (vehicles.get(i).equals(vehicleType + vehicleSeats)){
                    soldCount++;
                    sold = true;
                }
            }
            if(sold){
                String toRemove = vehicleType + vehicleSeats;
                vehicles.remove(toRemove);
                System.out.printf("Yes, sold for %d$%n", vehicleType * Integer.parseInt(vehicleSeats));
            } else {
                System.out.println("No");
            }

        }
        System.out.printf("Vehicles left: %s%n", String.join(", ", vehicles));
        System.out.printf("Vehicles sold: %d", soldCount);
    }
}
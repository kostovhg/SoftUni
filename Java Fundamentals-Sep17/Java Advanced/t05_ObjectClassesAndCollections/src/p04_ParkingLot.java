import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p04_ParkingLot {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        Set<String> nums = new HashSet<>();
        String[] input;
        while(!(input = scann.nextLine().split(", "))[0].equals("END")){
            if(input[0].equals("IN")){
                nums.add(input[1]);
            } else {
                nums.remove(input[1]);
            }
        }
        if(nums.size() == 0){
            System.out.println("Parking Lot is Empty");

        } else {
            for (String num :
                    nums) {
                System.out.println(num);
            }
        }


    }
}
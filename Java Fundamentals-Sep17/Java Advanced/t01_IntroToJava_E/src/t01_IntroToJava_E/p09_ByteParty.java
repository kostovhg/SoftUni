package t01_IntroToJava_E;

import java.util.Scanner;

public class p09_ByteParty {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Manipulate all input integers as follow:
        "-1 [position]" flip the bit at position
        "0 [position]" unset the bit at position
        "1 [position]" set the bit at position
        "party over" print resulting numbers on separate lines
         */
        Integer count = Integer.parseInt(input.nextLine());
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(input.nextLine());
        }
        String[] line;
        int pos = 0;
        while(!(line = input.nextLine().split("\\s"))[0].equals("party")){
            pos = Integer.parseInt(line[1]);
            String command = line[0];
            for (int i = 0; i < arr.length; i++) {
                switch(command){
                    case "-1":
                        arr[i] ^= 1 << pos;
                        break;
                    case "0":
                        arr[i] &= ~(1 << pos);
                        break;
                    case "1":
                        arr[i] |= 1 << pos;
                        break;
                }
            }
        }
        for (int i :
             arr) {
            System.out.println(i);
        }

    }
}
import java.util.Scanner;
import java.util.TreeSet;

public class p05_SoftUniParty {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        TreeSet<String> gests = new TreeSet();
        int invitations = 0;
        String input = scann.nextLine();
        while(!input.equals("PARTY")){
            gests.add(input);
            input = scann.nextLine();
        }
        invitations = gests.size();
        input = scann.nextLine();
        while(!input.equals("END")){
            --invitations;
            gests.remove(input);
            input = scann.nextLine();
        }
        System.out.println(invitations);
        for (String gest :
                gests   ) {
            System.out.println(gest);
        }
    }
}
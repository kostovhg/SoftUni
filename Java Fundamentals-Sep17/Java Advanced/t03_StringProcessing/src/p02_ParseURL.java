import java.util.Scanner;

public class p02_ParseURL {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[] url = sc.nextLine().split(":\\/\\/");
        String address = "";
        String resource = "";
        if (url.length != 2) {
            System.out.println("Invalid URL");
            return;
        }
        else {
            int index = url[1].indexOf('/');
            if (index < 0 ) {
                address = url[1];
            } else {
                address = url[1].substring(0, index);
                resource = url[1].substring(index + 1);
            }
        }

        System.out.println("Protocol = " + url[0]);
        System.out.println("Server = " + address);
        System.out.println("Resources = " +resource);

    }
}
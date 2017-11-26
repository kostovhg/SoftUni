package i_LinkedListTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        LinkedListImpl<Integer> list = new LinkedListImpl<>();
        int countOfCommands = Integer.parseInt(reader.readLine());
        String[] command = null;
        for (int i = 0; i < countOfCommands; i++) {
            command = reader.readLine().split("\\s+");
            switch (command[0]){
                case "Add":
                    list.add(Integer.parseInt(command[1]));
                    break;
                case "Remove":
                    list.remove(Integer.parseInt(command[1]));
                    break;
            }
        }

        System.out.println(list.getSize());
        for (Integer val :
                list) {
            System.out.print(val + " ");
        }
    }
}
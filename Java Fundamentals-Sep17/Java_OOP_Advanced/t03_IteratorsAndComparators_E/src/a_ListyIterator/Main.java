package a_ListyIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        ListyIterator<String> lIterator = null;
        while (!(line = reader.readLine()).equalsIgnoreCase("END")){
            String[] command = line.split("\\s+");
            switch (command[0]){
                case "Create":
                    lIterator =  new ListyIterator<>(Arrays.stream(command)
                                            .skip(1L).toArray(String[]::new));
                    break;
                case "Move":
                    System.out.println(lIterator.move());
                    break;
                case "Print":
                    lIterator.print();
                    break;
                case "HasNext":
                    System.out.println(lIterator.hasNext());
                    break;

            }
        }
    }
}

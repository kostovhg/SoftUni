package p07_CustomList;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        CustomList<String> myList = new CustomListImpl<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!"END".equals(line = reader.readLine())){
            String[] tokens = line.split("\\s+");
            switch (tokens[0]){
                case "Add": //Adds the given element to the end of the list
                    myList.add(tokens[1]);
                    break;
                case "Remove": //<index> - Removes the element at the given index
                    myList.remove(Integer.parseInt(tokens[1]));
                    break;
                case "Contains": //<element> - Prints if the list contains the given element (true or false)
                    System.out.println(myList.contains(tokens[1]));
                    break;
                case "Swap": // <index> <index> - Swaps the elements at the given indexes
                    myList.swap(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;
                case "Greater": // <element> - Counts the elements that are greater than the given element and prints their count
                    System.out.println(myList.countGreaterThan(tokens[1]));
                    break;
                case "Max": // - Prints the maximum element in the list
                    System.out.println(myList.getMax());
                    break;
                case "Min": // - Prints the minimum element in the list
                    System.out.println(myList.getMin());
                    break;
                case "Print": // - Prints all elements in the list, each on a separate line
                    for (String element : myList.get()) {
                        System.out.println(element);
                    }
                    break;
                case "END":
                    return; // - stops the reading of commands
                default: break;
            }
        }
    }
}

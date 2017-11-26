package c_StackIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        Stack customStack = new Stack();
        while (!(line = reader.readLine()).equalsIgnoreCase("END")){
            String[] command = line.split("\\s+");
            switch (command[0]){
                case "Push":
                    customStack.push(line.substring(line.indexOf(" ") + 1).split(", "));
                    break;
                case "Pop":
                    try{
                        customStack.pop();
                    } catch (NullPointerException npe) {
                        System.out.println(npe.getMessage());
                    }
                    break;
            }
        }
        print(customStack);
        print(customStack);
    }

    private static void print(Stack stack){
        stack.forEach(System.out::println);
    }
}

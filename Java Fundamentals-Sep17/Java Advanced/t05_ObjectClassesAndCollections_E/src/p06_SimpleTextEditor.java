import java.util.ArrayDeque;
import java.util.Scanner;

public class p06_SimpleTextEditor {
    private static ArrayDeque<StringBuilder> history = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        int commandsCount = Integer.parseInt(scann.nextLine());
        String[] command;
        history.push(new StringBuilder());
        for (int i = 0; i < commandsCount; i++) {
            command = scann.nextLine().split("\\s+");
            StringBuilder theText = new StringBuilder(history.peek());
            switch(command[0]){
                case "1":
                    history.push(theText.append(command[1]));
                    break;
                case "2":
                    theText.setLength(theText.length() - Integer.parseInt(command[1]));
                    theText.trimToSize();
                    history.push(theText);
                    break;
                case "3":
                    System.out.println(theText.charAt(Integer.parseInt(command[1]) - 1));
                    break;
                case "4":
                    history.pop();
                    break;
            }

        }
    }
}
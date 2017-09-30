import java.util.Scanner;

public class p00_StateMachine {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char[] s = "bookkeeper".toCharArray();
        int state = 0;
        int index = 0;
        char prev = '.';
        StringBuilder sb = new StringBuilder();
        while(index <= s.length){
            switch (state){
                case 0: // initial state
                    prev = s[index];
                    state = 1;
                    break;
                case 1: // new letter
                    sb.append(prev);
                    if (prev  == s[index]){
                        state = 2;
                    }
                    break;
                case 2: // same letter
                    prev = s[index - 1];
                    state = 1;
                    break;
            }
            ++index;
        }
        System.out.println(sb);
    }
}
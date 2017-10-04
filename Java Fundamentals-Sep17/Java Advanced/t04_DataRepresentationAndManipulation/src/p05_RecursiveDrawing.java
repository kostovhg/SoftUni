import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Collections;
import java.util.Scanner;

public class p05_RecursiveDrawing {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        recursiveDraw(num);

    }

    private static void recursiveDraw(int num) {
        // bottom
        if(num == 0) return;
        // pre-action
        System.out.println(String.
                join("", Collections.nCopies(num, "*")));
        recursiveDraw(num - 1);
        // post-action
        System.out.println(String.
                join("", Collections.nCopies(num, "#")));
    }
}
package f_CustomEnumAnnotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String category = reader.readLine();

        Class[] classes = {Card.class, Rank.class, Suit.class, Main.class};

        for (Class<?> cl : classes) {
            if(cl.getAnnotation(Info.class) == null) continue;
            Info info = cl.getAnnotation(Info.class);
            if (info.category().equals(category)) {
                System.out.println("Type = " + info.type() + ", Description = " + info.description());
            }
        }
    }
}

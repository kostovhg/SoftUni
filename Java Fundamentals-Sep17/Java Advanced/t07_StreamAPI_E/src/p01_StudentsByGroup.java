import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class p01_StudentsByGroup {

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("StudentsData.txt"));
        reader.lines()
                .map(x -> x.split("\\t"))
                .filter(x -> !x[0].equals("FN")
                        && Integer.valueOf(x[5]) == 2)
                .map(x -> String.format("%s %s", x[1], x[2]))
                .sorted()
                .forEach(System.out::println);
    }
}

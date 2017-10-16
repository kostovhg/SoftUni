import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class p10_GroupByGroup {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static class Person{
        private String firstName;
        private String secondName;
        private String fullName;
        private String group;

        public Person(String line){
            String[] tokens = line.split("\\s+");
            firstName = tokens[0];
            secondName = tokens[1];
            fullName = String.join(" ", tokens[0], tokens[1]);
            group = tokens[2];
        }

        public String getGroup(){
            return this.group;
        }
        public Integer getGroupAsNumber(){
            return Integer.valueOf(this.group);
        }
        public String getFillName(){
            return this.fullName;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Person> list = new ArrayList<>();
        String input;
        while(!"END".equals(input = reader.readLine())){
            list.add(new Person(input));
        }
        list.stream()
                .collect(Collectors.groupingBy(Person::getGroupAsNumber))
                .entrySet().stream()
                .forEachOrdered(g -> {
                    System.out.printf("%s - %s%n",g.getKey(),
                            g.getValue().stream()
                                    .map(Person::getFillName)
                    .collect(Collectors.joining(", ")));
                });
    }
}
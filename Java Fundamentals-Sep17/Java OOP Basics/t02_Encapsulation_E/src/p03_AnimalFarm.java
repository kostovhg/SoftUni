import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p03_AnimalFarm {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name = reader.readLine();
        int age = Integer.parseInt(reader.readLine());

        try {
            Chicken chicken = new Chicken(name, age);
            System.out.printf("Chicken %s (age %d) can produce %s eggs per day.",
                    chicken.getName(),
                    chicken.getAge(),
                    chicken.productPerDay());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Chicken {
    private String name;
    private int age;

    Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.isEmpty() || name.equals(" ")) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age < 0 || age > 15) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    private String calculateProductPerDay() {
        if (age < 6) return "2";
        else if (age < 12) return "1";
        else return "0.75";
    }

    String productPerDay(){
        return calculateProductPerDay();
    }
}

package e_WorkForce;

import e_WorkForce.contracts.Employee;
import e_WorkForce.contracts.EventRespondingList;
import e_WorkForce.models.JobList;
import e_WorkForce.models.Job;
import e_WorkForce.models.Observer;
import e_WorkForce.models.employees.PartTimeEmployee;
import e_WorkForce.models.employees.StandartEmployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        Observer observer = new Observer();
        EventRespondingList<Job> jobs = new JobList<>(observer);
        Map<String, Employee> employees = new HashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input;
        while (true) {
            input = reader.readLine().split("\\s+");

            if (input[0].equalsIgnoreCase("End")) {
                break;
            }

            switch (input[0]) {
                case "Job":
                    jobs.addObservableObject(new Job(input[1], Integer.parseInt(input[2]),
                            employees.get(input[3]), observer));
                    break;
                case "StandartEmployee":
                    employees.put(input[1], new StandartEmployee(input[1]));
                    break;
                case "PartTimeEmployee":
                    employees.put(input[1], new PartTimeEmployee(input[1]));
                    break;
                case "Pass":
                    jobs.update();
                    break;
                case "Status":
                    jobs.status();
                    break;
                default:
                    try {
                        Class<Employee> employeeClass =
                                (Class<Employee>) Class.forName("e_WorkForce.models.employees." + input[0]);
                        Constructor<Employee> constructor = employeeClass.getDeclaredConstructor((String.class));

                        employees.put(input[1], constructor.newInstance(input[1]));
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        System.out.println("There is no such class!");
                    }
                    break;
            }
        }
    }
}

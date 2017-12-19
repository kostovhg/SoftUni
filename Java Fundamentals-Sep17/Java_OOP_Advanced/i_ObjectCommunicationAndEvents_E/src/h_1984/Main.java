package h_1984;

import h_1984.contracts.Observable;
import h_1984.contracts.Observer;
import h_1984.factories.SubjectFactory;
import h_1984.models.Institution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Observable> subjects = new LinkedHashMap<>();
        Map<String, Observer> observers = new LinkedHashMap<>();

        int[] counts = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < counts[0]; i++) {
            Observable subject = SubjectFactory.create(reader.readLine());
            subjects.putIfAbsent(subject.getId(), subject);
        }

        String[] tokens;
        for (int i = 0; i < counts[1]; i++) {
            tokens = reader.readLine().split("\\s+");
            String[] varargs = Arrays.stream(tokens).skip(3).toArray(String[]::new);
            Observer observer = new Institution(tokens[1], tokens[2], varargs);
            observer.attachToSubjects(subjects);
            observers.put(observer.getId(), observer);
        }

        for (int i = 0; i < counts[2]; i++) {
            tokens = reader.readLine().split("\\s+");
            String currentId = tokens[0];
            String parameterToChange = tokens[1];
            String newValue = tokens[2];
            subjects.get(currentId).setState(parameterToChange, newValue);
        }

        for (String observerId : observers.keySet()) {
            System.out.println(observers.get(observerId));
        }
    }
}

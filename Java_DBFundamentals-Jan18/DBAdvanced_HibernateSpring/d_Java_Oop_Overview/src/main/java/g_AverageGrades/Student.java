package g_AverageGrades;

import java.util.Arrays;
import java.util.Comparator;

public class Student implements Comparable<Student>{

    private String name;
    private Double[] listOfGrades;
    private Double averageGrade;

    public Student(String[] args){
        this.setName(args[0]);
        this.setGrades(args);
        //this.setAverageGrade();
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setGrades(String[] grades) {
        this.listOfGrades = new Double[grades.length - 1];
        double sum = 0;
        for (int i = 1; i < grades.length; i++) {
            this.listOfGrades[i-1] = (Double.parseDouble(grades[i]));
            sum += this.listOfGrades[i-1];
        }
        this.averageGrade = 1.0d * sum / this.listOfGrades.length;
    }

    private void setAverageGrade() {
        this.averageGrade = (Arrays.stream(this.listOfGrades).mapToDouble(x -> x).average().getAsDouble());
    }

    public String getName() {
        return this.name;
    }

    public Double getAverageGrade() {
        return this.averageGrade;
    }

    @Override
    public String toString() {
        return String.format("%s -> %.2f", this.name, this.averageGrade);
    }

    @Override
    public int compareTo(Student student) {
        if(student.getName().equals(this.getName())){
            return student.getAverageGrade().compareTo(this.averageGrade);
        } else {
            return this.getName().compareTo(student.getName());
        }
    }
}
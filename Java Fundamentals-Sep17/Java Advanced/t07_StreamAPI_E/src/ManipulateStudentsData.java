import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ManipulateStudentsData {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Student> studentsList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("\n"
                + "Select solution:\n"
                + " 1) Students by Group\n"
                + " 2) Students by First and Last Name\n"
                + " 3) Students by Age\n"
                + " 4) Sort Students\n"
                + " 5) Filter Students by Email Domain\n"
                + " 6) Filter Students by Phone\n"
                + " 7) Excellent Students\n"
                + " 8) Weak Students\n"
                + " 9) Students Enrolled in 2014 or 2015\n"
                + " Please make a selection: ");
        studentsList = makeList();
        String input = sc.next();
        System.out.println();

        switch(input){
            case "1": printStudentsByGroup("2"); break;
            case "2": printStudentsByFirstAndLastName(); break;
            case "3": studentsByAge(18, 24); break;
            case "4": sortStudents(); break;
            case "5": filterStudentsByEmailDomain("gmail.com"); break;
            case "6": filterStudentsByPhone("2"); break;
            case "7": excellentStudents(); break;
            case "8": weakStudents(); break;
            case "9": studentsEnrolledIn2014or2015("2014, 2015"); break;
            default:
                System.out.println("End of Program");
        }

    }

    private static ArrayList<Student> makeList() {

        try{
            BufferedReader br = new BufferedReader(new FileReader("StudentsData.txt"));
            br.readLine();
            for (String line  = br.readLine(); line != null ; line = br.readLine()) {
                studentsList.add(new Student(line));
            }
        } catch (FileNotFoundException e){
            System.err.println("Error: Target File Cannot Be Read");
        } catch (Exception ex) {
            ex.printStackTrace(new PrintStream(System.out));
        }
        return studentsList;
    }

    private static void printStudentsByGroup(String input){

        studentsList.stream().filter(x ->
                x.getGroup() == Integer.parseInt(input))
                .sorted(Comparator.comparing(Student::getFirstName))
                .forEach(x ->
                        System.out.println(String.format("%s %s", x.getFirstName(), x.getFamilyName())));
    }

    private static void printStudentsByFirstAndLastName() {
        studentsList.stream()
                .filter(x -> x.getFirstName().compareTo(x.getFamilyName()) < 1)
                .forEach(x ->
                        System.out.println(String.format("%s %s",
                                x.getFirstName(), x.getFamilyName())));
    }

    private static void studentsByAge(int lo, int hi) {
        studentsList.stream()
                .filter(x -> (x.getAge() >= lo) && (x.getAge() <= hi))
                .forEach(x ->
                        System.out.println(String.format("%s %s %d",
                                x.getFirstName(),
                                x.getFamilyName(),
                                x.getAge())));
    }
    private static void sortStudents() {

        studentsList.stream()
                .sorted((s1, s2) -> {
                    if (s1.getFamilyName().equals(s2.getFamilyName())) {
                        return s2.getFirstName().compareTo(s1.getFirstName());
                    } else {
                        return s1.getFamilyName().compareTo(s2.getFamilyName());
                    }
                })
                .forEach(s ->
                        System.out.println(String.format("%s %s",
                                s.getFirstName(),
                                s.getFamilyName())));
    }

    private static void filterStudentsByEmailDomain(String domain){
        studentsList.stream()
                .filter(s -> (s.getEmail().split("@"))[1].equals(domain))
                .forEach(s ->
                        System.out.println(String.format("%s %s %s",
                                s.getFirstName(),
                                s.getFamilyName(),
                                s.getEmail())));
    }

    private static void filterStudentsByPhone(String code) {
        studentsList.stream()
                .filter(s -> (s.getPhone()).matches("^(\\+359|0)" + code + "\\d+"))
                .forEach(s ->
                        System.out.printf("%s %s %s%n",
                                s.getFirstName(),
                                s.getFamilyName(),
                                s.getPhone()));
    }

    private static void excellentStudents() {
        studentsList.stream()
                .filter(s ->
                        s.getGrades().stream().anyMatch(g -> g == 6))
                .forEach(s -> {
                        System.out.printf("%s %s ",
                                s.getFirstName(),
                                s.getFamilyName());
                    s.getGrades().stream()
                            .sorted(Comparator.reverseOrder())
                                .forEach(g ->
                                System.out.printf("%d ", g));
                    System.out.println();
                });
    }

    private static void weakStudents() {
        studentsList.stream()
                .filter(s ->
                        s.getGrades().stream()
                                .filter(x -> x <= 3).count() > 1)
                .sorted((s1, s2) ->
                    (s1.getGrades().stream().mapToInt(x -> x).sum()) -
                            (s2.getGrades().stream().mapToInt(x -> x).sum())
                )
                .forEach(s -> {
                        System.out.printf("%s %s ",
                                s.getFirstName(),
                                s.getFamilyName());
                        s.getGrades().stream()
                                .sorted()
                                .forEach(g ->
                                        System.out.printf("%d ", g));
                        System.out.println();
                });
    }

    private static void studentsEnrolledIn2014or2015(String years){
        /*
        // -> This part of code is can be used if we are solving the problem
        // -> according the description.
        String[] yearsDecade = Arrays.stream(years.split(",?\\s+"))
                .map(x -> x.trim().substring(2,3).toString())
                .toArray(size -> new String[size]);
        studentsList.stream()
                .filter(s -> Arrays.asList(yearsDecade)
                        .contains(s.getfNum().substring(4,5)))
                .forEach(s -> {
                    s.getGrades().stream()
                            .forEach(g -> System.out.printf("%d ", g));
                    System.out.println();
                });
                */
        // obviously, the output should be sorted by years and names alphabetically
        studentsList.stream()
                .collect(Collectors.groupingBy(Student::getRollYear))
                .entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(x -> {
            System.out.printf("20%d:%n", x.getKey());
            x.getValue().stream()
                    .sorted(Comparator.comparing(Student::getFirstName)
                            .thenComparing(Student::getFamilyName))
                    .forEach(n ->
                            System.out.printf("-- %s %s%n",
                                    n.getFirstName(),
                                    n.getFamilyName()));
                });
    }
}
package f_StrategyPattern;

import java.util.Comparator;

public class CompareByName implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        String firstPersonName = o1.getName().toLowerCase();
        int firstPersonNameLen = firstPersonName.length();
        String secondPersonName = o2.getName().toLowerCase();
        int secondPersonNameLen = secondPersonName.length();
        if (firstPersonNameLen == secondPersonNameLen) {
            return firstPersonName.compareTo(secondPersonName);
        }
        return Integer.compare(firstPersonNameLen, secondPersonNameLen);
    }
}

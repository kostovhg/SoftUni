package f_StrategyPattern;

import java.util.Comparator;

public class CompareByAge implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}

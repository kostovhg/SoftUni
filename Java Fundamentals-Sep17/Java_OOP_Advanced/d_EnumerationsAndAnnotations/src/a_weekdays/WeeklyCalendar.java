package a_weekdays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.naturalOrder;

public class WeeklyCalendar {

    private List<WeeklyEntry> entries;

    public WeeklyCalendar(){
        this.entries = new ArrayList<>();
    }

    public void addEntry(String weekday, String notes){
        this.entries.add(new WeeklyEntry(weekday, notes));
    }

    public Iterable<WeeklyEntry> getWeeklySchedule(){
        //this.entries.sort(Comparator.comparingInt(d -> d.getWeekday().ordinal()));
        this.entries.sort(naturalOrder());
        return this.entries;
    }
}

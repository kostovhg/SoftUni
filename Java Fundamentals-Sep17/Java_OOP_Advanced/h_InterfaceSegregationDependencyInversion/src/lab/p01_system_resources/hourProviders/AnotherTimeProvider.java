package lab.p01_system_resources.hourProviders;

import lab.p01_system_resources.contracts.TimeProvider;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AnotherTimeProvider implements TimeProvider {
    @Override
    public int getHour() {
        Date date = new Date(); // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a calendar
        calendar.setTime(date); // assigns calendar to given date
        return calendar.get(Calendar.HOUR_OF_DAY); // return hour in 24h format
    }
}

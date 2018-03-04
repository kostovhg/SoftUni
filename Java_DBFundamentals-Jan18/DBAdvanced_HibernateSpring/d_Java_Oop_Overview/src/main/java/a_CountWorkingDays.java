import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class a_CountWorkingDays {

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static Calendar CAL = Calendar.getInstance();
    private static final Map<Date, String> NATIONAL_HOLIDAYS = createHolidaysMap();

    private static HashMap<Date, String> createHolidaysMap() {
        HashMap<Date, String> holidays = new HashMap<Date, String>();
        try {
            holidays.put(DATE_FORMAT.parse("01-01-2000"), "New year Eve");
            holidays.put(DATE_FORMAT.parse("03-03-2000"), "Liberation Day");
            holidays.put(DATE_FORMAT.parse("01-05-2000"), "Worker’s day");
            holidays.put(DATE_FORMAT.parse("06-05-2000"), "Saint George’s Day");
            holidays.put(DATE_FORMAT.parse("24-05-2000"), "Saints Cyril and Methodius Day");
            holidays.put(DATE_FORMAT.parse("06-09-2000"), "Unification Day");
            holidays.put(DATE_FORMAT.parse("22-09-2000"), "Independence Day");
            holidays.put(DATE_FORMAT.parse("01-11-2000"), "National Awakening Day");
            holidays.put(DATE_FORMAT.parse("24-12-2000"), "Christmas");
            holidays.put(DATE_FORMAT.parse("25-12-2000"), "Christmas");
            holidays.put(DATE_FORMAT.parse("26-12-2000"), "Christmas");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return holidays;
    }

    public static void main(String[] args) throws IOException, ParseException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Get dates
        Date firstDate = DATE_FORMAT.parse(reader.readLine());
        Date secondDate = DATE_FORMAT.parse(reader.readLine());

        // Add a day to second date to include it in the calculations
        secondDate.setTime(secondDate.getTime() + 24 * 60 * 60 * 1000);

        CAL.setTime(firstDate);


        int workingDays = 0;

        // loop from start to end dates (inclusive)
        while ((CAL.getTime()).before(secondDate)) {
            int dayOfWeek = CAL.get(Calendar.DAY_OF_WEEK);
            // System.out.println(CAL.getTime());
            if (dayOfWeek != Calendar.SUNDAY &&
                    dayOfWeek != Calendar.SATURDAY &&
                    !isAHolyday(CAL.getTime())) {
                workingDays++;
            }
            CAL.add(Calendar.DATE, 1);
        }

        System.out.println(workingDays);
    }

    private static boolean isAHolyday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, 2000);
        // System.out.println(cal.getTime());
        return NATIONAL_HOLIDAYS.containsKey(cal.getTime());
    }
}

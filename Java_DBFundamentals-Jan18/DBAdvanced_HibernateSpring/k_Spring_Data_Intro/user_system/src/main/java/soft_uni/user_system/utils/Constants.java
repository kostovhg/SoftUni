package soft_uni.user_system.utils;

import java.util.*;

public class Constants {

    public static final String PROJECT_FOLDER = System.getProperty("user.dir");
    public static final String RESOURCES_FOLDER = PROJECT_FOLDER + "/src/main/java/resources/";
    public static final String AVATARS_FOLDER = RESOURCES_FOLDER + "avatars/";

    public static final Map<String, String[]> TOWNS = createTowns();
    public static final Set<String[]> USERS = createUsers();

    private static Map<String, String[]> createTowns() {
        Map<String, String[]> towns = new HashMap<>();

        towns.put("Bulgaria", new String[]{
                "Sofia", "Plovdiv", "Varna", "Burgas", "Ruse",
                "Stara Zagora", "Pleven", "Dobrich", "Sliven", "Shumen"});
        towns.put("Germany", new String[]{
                "Berlin", "Hamburg", "Munich", "Cologne", "Frankfurt am Main",
                "Stuttgart", "Düsseldorf", "Dortmund", "Essen"});
        return towns;
    }

    private static Set<String[]> createUsers() {
        Set<String[]> usersParams = new HashSet<>();
        // array properties : username, password, email, reg_date
        usersParams.add(new String[]{"peshko99", "OxKBaI_IAp0/|A", "pesho_petrov@abv.bg", "2012-03-13"});
        usersParams.add(new String[]{"ivansProfile", "dc)H(VxC`b5$K5(", "ivan_ivanov2000@gmail.com", "2017-06-02"});
        usersParams.add(new String[]{"mitkoDimitrov", "2w(%8>zNu4cQS7eP", "m.dimitrov@yahoo.com", "2016-02-12"});
        usersParams.add(new String[]{"mimi_mimi", "KpZ_[TS*mqh%6C4)", "maria2015@abv.bg", "2016-10-22"});
        usersParams.add(new String[]{"tinka_stamenova", "KpZ_[TS*mqh%6C4)", "tincheto95@abv.bg", "2013-08-17"});
        usersParams.add(new String[]{"midnight_stalker", "fZN\\Hnd%$rUB/L9r", "georgi_avramov@gmail.com", "2016-04-06"});
        usersParams.add(new String[]{"radka66", "OxKBaI_IAp0/|A", "radostina_d@abv.bg", "2012-04-13"});
        usersParams.add(new String[]{"sushumushu", "dc)H(VxC`b5$K5(", "m_stankova@gmail.com", "2017-01-02"});
        usersParams.add(new String[]{"sunnybunny", "2w(%8>zNu4cQS7eP", "katya_prodanova@yahoo.com", "2016-06-12"});
        usersParams.add(new String[]{"wrongMail", "fdli_!B/L9r", "alicia@boncho.", "2016-04-06"});
        usersParams.add(new String[]{"itso_petrov", "KpZ_[TS*mqh%6C4)", "h.petrov@abv.bg", "2016-11-22"});
        usersParams.add(new String[]{"fear_OOD", "KpZ_[TS*mqh%6C4)", "m_avramov@abv.bg", "2013-09-17"});
        usersParams.add(new String[]{"mitaka", "fZN\\Hnd%$rUB/L9r", "mgeorgiev@gmail.com", "2016-04-06"});
        usersParams.add(new String[]{"wrongMail", "fdli_!B/L9r", "_a@.com", "2016-04-06"});
        usersParams.add(new String[]{"wrongPass", "parola", "nomather@what.com", "2016-04-06"});
        usersParams.add(new String[]{"wrongPassLength", "p", "nomather@what.com", "2016-04-06"});

        return  usersParams;
    }

}

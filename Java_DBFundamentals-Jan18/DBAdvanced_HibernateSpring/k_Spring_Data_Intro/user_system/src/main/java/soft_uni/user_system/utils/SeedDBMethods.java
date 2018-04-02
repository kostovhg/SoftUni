package soft_uni.user_system.utils;

import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.entities.townEntity.Country;
import soft_uni.user_system.models.entities.townEntity.Town;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static soft_uni.user_system.utils.Constants.TOWNS;
import static soft_uni.user_system.utils.Constants.USERS;

public class SeedDBMethods {

    public static Map<Country, Set<Town>> GetTownsAndCountries(){
        Map<Country, Set<Town>> townsMap = new HashMap<>();
        for (String countryName : TOWNS.keySet()) {
            Country country = new Country(countryName);
            townsMap.put(country, new HashSet<>());
            for (String town : TOWNS.get(countryName)) {
                townsMap.get(country).add(new Town(town));
            }
        }
        return townsMap;
    }

    public Set<User> GetUsers() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Set<User> usersSet = new HashSet<>();
        for (String[] user : USERS) {
            usersSet.add(new User(user[0], user[1], user[2], dateFormat.parse(user[3])));

        }
        return usersSet;
    }
}

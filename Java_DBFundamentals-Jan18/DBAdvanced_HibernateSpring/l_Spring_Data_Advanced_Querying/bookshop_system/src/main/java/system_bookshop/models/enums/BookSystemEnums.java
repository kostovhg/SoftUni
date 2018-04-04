package system_bookshop.models.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BookSystemEnums {

    public static String getEnumAsStringList(Class<? extends Enum> clas) {
        return Arrays.stream(clas.getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.joining(", "));
    }
}

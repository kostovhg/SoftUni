package g_MirrorImage;

import java.util.LinkedHashMap;
import java.util.Map;

public class Repository {

    private static final Map<Integer, Wizard> wizards = new LinkedHashMap<>();

    public static void add(Wizard wizard) {
        wizards.putIfAbsent(wizard.getId(), wizard);
    }

    public static Wizard get(int id) {
        return wizards.get(id);
    }
}

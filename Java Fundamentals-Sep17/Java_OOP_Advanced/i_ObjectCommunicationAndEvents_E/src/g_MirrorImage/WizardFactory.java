package g_MirrorImage;

public class WizardFactory {

    private static int id = 0;

    public static Wizard create(String name, int power) {

        return new Wizard(id++, name, power);
    }

}

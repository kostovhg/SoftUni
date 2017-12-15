package f_KingsGambitExtended.models;

public class Footman extends KingsUnit {

    public static final int FOOTMAN_HEALTH = 2;

    public Footman(String name) {
        super(name, FOOTMAN_HEALTH);
    }

    @Override
    public void notifyCurrent(){
        System.out.println(String.format("Footman %s is panicking!", super.getName()));
    }
}

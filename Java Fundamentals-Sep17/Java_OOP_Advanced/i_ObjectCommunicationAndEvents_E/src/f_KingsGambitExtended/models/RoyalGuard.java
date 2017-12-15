package f_KingsGambitExtended.models;

public class RoyalGuard extends KingsUnit {

    public static final int ROYAL_GUARD_HEALTH = 3;

    public RoyalGuard(String name) {
        super(name, ROYAL_GUARD_HEALTH);
    }

    @Override
    public void notifyCurrent(){
        System.out.println(String.format("Royal Guard %s is defending!", super.getName()));
    }

}

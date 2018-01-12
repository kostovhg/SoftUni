package app.models.participants;

import app.models.Config;

public class Boss extends Participant {

    public Boss() {
        super();
        super.receiveReward(Config.BOSS_GOLD);
        super.setHealth(Config.BOSS_HEALTH);
    }

//    @Override
//    public String attack(Targetable target) {
//        return null;
//    }

    @Override
    public double getDamage() {
        return Config.BOSS_DAMAGE;
    }

    @Override
    public void receiveReward(double reward) {
        // should receive only 10 percent of heroes gold
        super.receiveReward((reward * 10) / 100);
    }

    @Override
    public void levelUp() {
        super.setHealth(Config.BOSS_HEALTH);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(String.format(" | %.2f Gold", super.getGold()));
        return sb.toString();
    }
}

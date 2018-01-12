package app.models.actions;

import app.contracts.Action;
import app.contracts.Targetable;
import app.models.Config;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class BossFight implements Action {

    private Targetable boss;
    private Deque<Targetable> participants;

    public BossFight(){
        this.boss = null;
        this.participants = new ArrayDeque<>();
    }

    @Override
    public String executeAction(List<Targetable> participants) {

        for (Targetable participant : participants) {
            this.participants.addLast(participant);
        }
        this.boss = this.participants.removeFirst();

        if (!this.boss.getClass().getSimpleName().equals("Boss")) {
            return "Invalid boss.";
        }

        if (participants.size() < 1) {
            return "There should be at least 1 participant for boss fight!";
        }

        StringBuilder sb = new StringBuilder();

        Targetable currentHero;

        while (true) {
            currentHero = this.participants.removeFirst();
            this.boss.setHealth(this.boss.getHealth() - currentHero.getDamage());
            if (this.boss.isAlive()) {
                currentHero.setHealth(currentHero.getHealth() - this.boss.getDamage());
            } else {
                sb.append("Boss has been slain by: ");
                this.boss.giveReward(currentHero);
                this.participants.addLast(currentHero);
                for (Targetable participant : this.participants.stream().sorted(Comparator.comparing(Targetable::getName)).collect(Collectors.toList())) {
                    participant.levelUp();
                    participant.receiveReward(Config.BOSS_INDIVIDUAL_REWARD);
                    sb.append(System.lineSeparator());
                    sb.append(participant.toString());
                }
                break;
            }
            if (currentHero.isAlive()) {
                this.participants.addLast(currentHero);
            }
            if (this.participants.size() < 1) {
                sb.append("Boss has slain them all!");
                break;
            }
        }

        return sb.toString();
    }
}

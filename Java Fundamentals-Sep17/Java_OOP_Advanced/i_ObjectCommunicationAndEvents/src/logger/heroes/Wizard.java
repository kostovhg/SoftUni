package logger.heroes;

import logger.abstractClasses.AbstractHero;
import logger.contracts.Handler;
import logger.contracts.ObservableTarget;
import logger.enums.LogType;

public class Wizard extends AbstractHero {
    private static final String ATTACK_MESSAGE = "%s damages %s for %s";

    public Wizard(String id, int dmg, Handler combatLogger) {
        super(id, dmg, combatLogger);
    }

    @Override
    protected void executeClassSpecificAttack(ObservableTarget target, int dmg) {
        super.getHandler().handle(LogType.MAGIC, String.format(ATTACK_MESSAGE, this, target, dmg));
        target.receiveDamage(dmg);
    }

    @Override
    public void update(int reward){
        super.getHandler().handle(LogType.EVENT, super.getId() + " wins rewartd " + reward);
    }
}

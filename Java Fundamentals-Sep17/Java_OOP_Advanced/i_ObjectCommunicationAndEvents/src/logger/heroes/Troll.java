package logger.heroes;

import logger.abstractClasses.AbstractTarget;
import logger.contracts.Handler;

public class Troll extends AbstractTarget {

    public Troll(String id, int hp, int reward, Handler handler) {
        super(id, hp, reward, handler);
    }

}

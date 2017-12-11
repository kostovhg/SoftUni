package logger.heroes;

import logger.abstractClasses.AbstractTarget;
import logger.contracts.Handler;

public class Dragon extends AbstractTarget {

    public Dragon(String id, int hp, int reward, Handler handler) {
        super(id, hp, reward, handler);
    }

}

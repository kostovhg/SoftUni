package logger;

import logger.abstractClasses.Logger;
import logger.contracts.Attacker;
import logger.contracts.Target;
import logger.loggers.CombatLogger;
import logger.loggers.ErrorLogger;
import logger.loggers.EventLogger;

public class Main {

    public static void main(String[] args) {

        Logger combatLogger = new CombatLogger();
        Logger eventLogger = new EventLogger();
        Logger errorLogger = new ErrorLogger();
        //eventLogger.setSuccessor(errorLogger);
        combatLogger.setSuccessor(eventLogger);


        logger.contracts.Executor command = new logger.CommandExecutor();


        Attacker attacker = new Warrior("Pesho", 10, combatLogger);
        Target target = new Dragon("Gosho", 10, 5, combatLogger);

        /* for first problem
        combatLogger.handle(LogType.ATTACK, "Some attack");
        combatLogger.handle(LogType.ERROR, "Some error");
        combatLogger.handle(LogType.EVENT, "Some event");*/


        logger.contracts.Command setTarget = new logger.commands.TargetCommand(attacker, target);
        logger.contracts.Command attack = new logger.commands.AttackCommand(attacker);

        command.executeCommand(setTarget);
        command.executeCommand(attack);
    }
}

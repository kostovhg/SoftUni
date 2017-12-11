package logger;

import logger.abstractClasses.Logger;
import logger.commands.GroupAttackCommand;
import logger.commands.GroupTargetCommand;
import logger.contracts.*;
import logger.heroes.Dragon;
import logger.heroes.Warrior;
import logger.heroes.Wizard;
import logger.loggers.CombatLogger;
import logger.loggers.ErrorLogger;
import logger.loggers.EventLogger;

public class Main {

    public static void main(String[] args) {

        Logger combatLogger = new CombatLogger();
        Logger eventLogger = new EventLogger();
        Logger errorLogger = new ErrorLogger();
        eventLogger.setSuccessor(errorLogger);
        combatLogger.setSuccessor(eventLogger);

        logger.contracts.Executor command = new logger.CommandExecutor();

        Attacker firstAttacker = new Warrior("Pesho", 10, combatLogger);
        Attacker secondAttacker = new Warrior("Gosho", 11, combatLogger);
        Attacker thirdAttacker = new Wizard("Stamat", 2, combatLogger);
        Attacker forthAttacker = new Warrior("Jivko", 12, combatLogger);
        Attacker fifthAttacker = new Wizard("Ivan", 8, combatLogger);
        ObservableTarget target = new Dragon("Smoug", 41, 5, combatLogger);

        AttackGroup group = new Group();
        group.addMember(firstAttacker);
        group.addMember(secondAttacker);
        group.addMember(thirdAttacker);
        group.addMember(forthAttacker);
        group.addMember(fifthAttacker);

        target.register(firstAttacker);
        target.register(secondAttacker);
        target.register(thirdAttacker);
        target.register(forthAttacker);
        target.register(fifthAttacker);

        Command setTarget = new GroupTargetCommand(group, target);
        Command attackCommand = new GroupAttackCommand(group);

        /* for first problem
        combatLogger.handle(LogType.ATTACK, "Some attack");
        combatLogger.handle(LogType.ERROR, "Some error");
        combatLogger.handle(LogType.EVENT, "Some event");
        */

        /* for second problem
        logger.contracts.Command setTarget = new logger.commands.TargetCommand(attacker, target);
        logger.contracts.Command attackCommand = new logger.commands.AttackCommand(attacker);
        */

        command.executeCommand(setTarget);
        command.executeCommand(attackCommand);


    }
}

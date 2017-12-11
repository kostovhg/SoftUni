package logger.commands;

import logger.contracts.AttackGroup;
import logger.contracts.Command;

public class GroupAttackCommand implements Command {

    private AttackGroup attackers;

    public GroupAttackCommand(AttackGroup attackers) {
        this.attackers = attackers;
    }

    @Override
    public void execute() {
        this.attackers.groupAttack();
    }
}

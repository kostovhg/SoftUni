package logger.commands;

import logger.contracts.*;

public class GroupTargetCommand implements Command {

    private AttackGroup attackers;
    private ObservableTarget target;

    public GroupTargetCommand(AttackGroup attackers, ObservableTarget target) {
        this.attackers = attackers;
        this.target = target;
    }

    @Override
    public void execute() {
        this.attackers.groupTarget(this.target);
    }
}

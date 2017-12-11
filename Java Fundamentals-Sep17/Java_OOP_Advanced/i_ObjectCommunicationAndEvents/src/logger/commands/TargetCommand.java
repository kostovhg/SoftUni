package logger.commands;

import logger.contracts.Attacker;
import logger.contracts.Command;
import logger.contracts.ObservableTarget;
import logger.contracts.Target;

public class TargetCommand implements Command {
    private Attacker attacker;
    private ObservableTarget target;

    public TargetCommand(Attacker attaker, ObservableTarget target){
        this.attacker = attaker;
        this.target = target;
    }

    @Override
    public void execute() {
        this.attacker.setTarget(this.target);
    }
}

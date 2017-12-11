package logger.commands;

import logger.contracts.Attacker;
import logger.contracts.Command;
import logger.contracts.Target;

public class TargetCommand implements Command {
    private Attacker attacker;
    private Target target;

    public TargetCommand(Attacker attaker, Target target){
        this.attacker = attaker;
        this.target = target;
    }

    @Override
    public void execute() {
        this.attacker.setTarget(this.target);
    }
}

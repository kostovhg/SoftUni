package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class RetireCommand extends Command {

    public static final String RETIRE_MESSAGE_FORMAT = "%s retired!";

    public RetireCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        try {
            super.getRepository().removeUnit(super.getData()[1]);
        } catch (IllegalStateException ise){
            return ise.getMessage();
        }
        return String.format(RETIRE_MESSAGE_FORMAT, super.getData()[1]);
    }
}

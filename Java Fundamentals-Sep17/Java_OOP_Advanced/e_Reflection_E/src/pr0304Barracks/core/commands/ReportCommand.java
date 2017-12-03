package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class ReportCommand extends Command {

    @Override
    public String execute() {
        String output = super.getRepository().getStatistics();
        return output;
    }
}

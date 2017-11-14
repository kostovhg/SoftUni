package bg.softuni.contracts;

import bg.softuni.contracts.DatabaseInterfaces.FilteredTaker;
import bg.softuni.contracts.DatabaseInterfaces.OrderedTaker;
import bg.softuni.contracts.DatabaseInterfaces.Requester;

import java.io.IOException;

public interface Database extends
        Requester,
        FilteredTaker,
        OrderedTaker {

    void loadData(String fileName) throws IOException;

    void unloadData() throws IOException;
}

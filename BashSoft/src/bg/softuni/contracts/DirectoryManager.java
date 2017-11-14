package bg.softuni.contracts;

import bg.softuni.contracts.DirectoryInterfaces.DirectoryChanger;
import bg.softuni.contracts.DirectoryInterfaces.DirectoryCreator;
import bg.softuni.contracts.DirectoryInterfaces.DirectoryTraverser;

public interface DirectoryManager extends
        DirectoryChanger,
        DirectoryTraverser,
        DirectoryCreator {
}

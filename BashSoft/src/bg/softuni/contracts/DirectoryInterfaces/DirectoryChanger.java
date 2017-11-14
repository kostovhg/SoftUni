package bg.softuni.contracts.DirectoryInterfaces;

public interface DirectoryChanger {
    void changeCurrentDirRelativePath(String relativePath);

    void changeCurrentDirAbsolute(String absolutePath);
}

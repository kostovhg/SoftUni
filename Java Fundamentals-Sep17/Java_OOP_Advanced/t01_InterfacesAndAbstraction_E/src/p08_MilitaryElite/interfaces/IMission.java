package p08_MilitaryElite.interfaces;

public interface IMission {

    String getState();

    String getCodeName();

    void completeMission();

    @Override
    String toString();
}

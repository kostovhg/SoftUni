package app.contracts;

public interface Battlefield {

    void createAction(String actionName, String... participantNames);

    void createParticipant(String heroName, String heroClassName);

    void createSpecial(String heroName, String specialName);

    void reportParticipants();

    void reportActions();
}

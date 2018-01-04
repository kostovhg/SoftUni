package pawInc.contracts;

public interface Manager {
    void registerCleansingCenter(String name);

    void registerAdoptionCenter(String name);

    void registerCastrationCenter(String name);

    void registerDog(String name, int age, int learnedCommands, String adoptionCenterName);

    void registerCat(String name, int age, int intelligenceCoefficient, String adoptionCenterName);

    void sendForCleansing(String adoptionCenterName, String cleansingCenterName);

    void sendForCastration(String adoptionCenterName, String castrationCenterName);

    void cleanse(String cleansingCenterName);

    void castrate(String castrationCenterName);

    void adopt(String adoptionCenterName);

    void printStatistics();

    void castrationStatistics();

    long countAnimals();
}

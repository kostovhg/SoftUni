package pawInc.contracts;

import pawInc.entities.animals.Animal;

import java.util.List;

public interface ICenter {

    String getName();

    List<IAnimal> getStoredAnimals();

    void register(IAnimal animal);

    void register(List<IAnimal> animals);

    /**
     * Send animals to specific center:
     *  to Castration center to wait for castration
     *  to Adoption center to wait for adoption
     *  to Cleansing center to wait for cleansing;
     *  in case the center is null - animals are being processed by
     *  Animal Center Manager (adopted or returned to their initial centers for adoption
     * @param destinationCenter - center that will receive the animals
     * @return - a list of animals being transferred from current center to destination;
     */
    List<IAnimal> proceed(ICenter destinationCenter);
}

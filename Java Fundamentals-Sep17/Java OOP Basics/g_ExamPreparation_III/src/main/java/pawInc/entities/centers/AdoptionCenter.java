package pawInc.entities.centers;

import pawInc.contracts.IAnimal;
import pawInc.contracts.ICenter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdoptionCenter extends Center {

    public AdoptionCenter(String name) {
        super(name);
    }

    @Override
    public List<IAnimal> proceed(ICenter destinationCenter){
        Predicate<IAnimal> cleansed = (destinationCenter != null) ?
                (a -> !a.isCleansed()) :
                (IAnimal::isCleansed);
            List<IAnimal> animalsForProcessing = super.getStoredAnimals().stream()
                    .filter(cleansed).collect(Collectors.toList());
            super.removeAll(animalsForProcessing);
            if(destinationCenter != null) destinationCenter.register(animalsForProcessing);
            return animalsForProcessing;
    }
}

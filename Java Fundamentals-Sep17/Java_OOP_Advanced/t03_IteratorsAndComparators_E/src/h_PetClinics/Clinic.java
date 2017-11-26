package h_PetClinics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Clinic implements Iterable<Pet> {

    private static final String INVALID_OPERATION = "Invalid Operation!";
    private String name;
    private Pet[] pets;
    private List<Integer> accommodationIndexes;

    public Clinic(String... clinicData) {
        this.setName(clinicData[0]);
        this.setRooms(clinicData[1]);
        this.setAccommodationIndexes();
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setRooms(String roomsCount) {
        int count = Integer.parseInt(roomsCount);
        if (count % 2 == 0) {
            throw new IllegalArgumentException(INVALID_OPERATION);
        }
        this.pets = new Pet[count];
    }

    private void setAccommodationIndexes() {
        this.accommodationIndexes = new ArrayList<>();
        int startIndex = this.pets.length / 2;
        this.accommodationIndexes.add(startIndex);
        if (startIndex == 0) {
            return;
        }
        for (int i = 1; i < this.pets.length; i++) {
            this.accommodationIndexes.add(
                    this.accommodationIndexes.get(i - 1) +
                            ((i % 2 == 0) ?
                                    (i) : (-i))
            );
        }

    }

    public boolean addPet(Pet petToBeAdded) {
        Iterable<Integer> indexes = this.accommodationIndexes;

        for (Integer index : indexes) {
            if (this.pets[index] == null) {
                this.pets[index] = petToBeAdded;
                return true;
            }
        }
        return false;
    }

    public boolean releasePet() {
        Iterator<Pet> releaseIterator = new ReleaseIterator();
        while (releaseIterator.hasNext()) {
            if (releaseIterator.next() != null) {
                releaseIterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean hasEmptyRoom() {
        return Arrays.asList(this.pets).contains(null);
    }

    public Pet getPetByIndex(String strIndex){
        int index = Integer.parseInt(strIndex);
        return this.pets[index - 1];
    }

    @Override
    public Iterator<Pet> iterator() {
        return new ClinicIterator();
    }


    private final class ClinicIterator implements Iterator<Pet> {
        private int index;

        public ClinicIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < pets.length;
        }

        @Override
        public Pet next() {
            return pets[this.index++];
        }
    }

    private final class ReleaseIterator implements Iterator<Pet> {

        private int index;
        private List<Integer> releaseIndexes;

        public ReleaseIterator() {
            this.index = 0;
            this.setReleaseIndexes();
        }

        @Override
        public boolean hasNext() {
            return this.index < this.releaseIndexes.size();
        }

        @Override
        public Pet next() {
            return pets[this.releaseIndexes.get(this.index++)];
        }

        @Override
        public void remove() {
            pets[this.releaseIndexes.get(this.index - 1)] = null;
        }

        private void setReleaseIndexes() {
            this.releaseIndexes = new ArrayList<>();
            int startIndex = pets.length / 2;
            this.releaseIndexes.add(startIndex);
            if (startIndex == 0) {
                return;
            }

            for (int i = 1; i < pets.length; i++) {
                if (i > startIndex) {
                    this.releaseIndexes.add((i - 1) - startIndex);
                } else {
                    this.releaseIndexes.add(startIndex + i);
                }
            }
        }
    }
}
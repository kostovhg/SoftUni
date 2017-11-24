package d_Froggy;

import java.util.*;

public class Lake implements Iterable<Integer> {

    private List<Integer> lake;

    public Lake(String... lakeElementsAsString) {
        this.lake = new ArrayList<>();
        for (String element : lakeElementsAsString) {
            if(element != "") {
                this.lake.add(Integer.valueOf(element));
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }

    private class Frog implements Iterator<Integer>{

        private int nextNumber = 0;
        private int size = lake.size();

        @Override
        public boolean hasNext() {
            if(this.nextNumber % 2 == 0 && this.size - this.nextNumber < 1) {
                this.nextNumber = 1;
            }
            return this.nextNumber < this.size;
        }

        @Override
        public Integer next() {
            Integer toReturn = lake.get(nextNumber);
            nextNumber += 2;
            return toReturn;

        }
    }

}

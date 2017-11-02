package p05_RandomArrayLIst;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList{
    Random rnd = new Random();
    public Object getRandomElement(){
        int index = rnd.nextInt(super.size());
        return super.remove(index);
        /*
        Can't remove last element!?
        int index = rnd.nextInt(super.size());
        Object element = super.get(index);
        super.set(
                index, super.remove(super.size() - 1));
        return element;*/
    }

}

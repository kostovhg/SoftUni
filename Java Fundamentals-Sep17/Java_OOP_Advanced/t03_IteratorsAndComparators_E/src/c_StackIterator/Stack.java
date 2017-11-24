package c_StackIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Stack implements Iterable<Integer> {

    private List<Integer> theList;
    //private int index = 0;

    public Stack(String... aCollection) {
        this.theList = new ArrayList<>();
        push(aCollection);
    }

    public void push(String... elements) {
        for (String element : elements) {
            this.theList.add(Integer.parseInt(element.trim()));
            //index++;
        }
    }

    public Integer pop(){
        if(theList.size() > 0){
            return this.theList.remove(theList.size() - 1);
        } else {
            throw new NullPointerException("No elements");
        }
    }


    @Override
    public Iterator<Integer> iterator() {
        return new CustomStackIterator();
    }

    private class CustomStackIterator implements Iterator<Integer> {

        private int nextIndex = theList.size() - 1;

        @Override
        public boolean hasNext() {
            return this.nextIndex > -1;
        }

        @Override
        public Integer next() {
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }
            return theList.get(nextIndex--);
        }
    }
}

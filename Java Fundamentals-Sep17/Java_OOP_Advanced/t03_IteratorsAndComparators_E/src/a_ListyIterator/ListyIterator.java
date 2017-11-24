package a_ListyIterator;

import java.util.*;

public class ListyIterator<S> implements Iterable<S>{

    // This contains the actual elements of the list
    private List<S> list;
    // Instance of private iterator
    //private ListyItter iterator;
    // Current index
    private int index;

    // Constructor thath takes "raw" list and stores it
    public ListyIterator(S... inputList) {
        this.list = new LinkedList<>(Arrays.asList(inputList));
        this.index = 0;
        //this.iterator = new ListyItter();
    }

    // Move the internal index to next one if it exist. Return false if next doesn't exist
    public boolean move(){
        if(this.hasNext()){
            this.index++;
            return true;
        }
        return false;
    }

    // false if current index is last one, true if there are one or more elements in the list
    public boolean hasNext(){
        return this.index < this.list.size() - 1;
    }

    // Print the element at current position. On empty list prints error message.
    public void print(){
        if(this.list.size() > 0) {
            System.out.println(this.list.get(index));
        } else {
            System.out.println("Invalid Operation!");
        }
    }

    // Necessary for "simple" for loop
    @Override
    public Iterator<S> iterator() {
        return new ListyItter();
    }

    //
    private class ListyItter implements Iterator<S>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < list.size() - 1;
        }

        @Override
        public S next() {
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }
            return list.get(this.current++);
        }
    }
}

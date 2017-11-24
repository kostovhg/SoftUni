package b_Collection;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.function.Consumer;

public class ListyIterator<S> implements Iterable<S> {

    private List<S> theList;
    private int index;

    public ListyIterator(S... someList) {
        this.theList = new ArrayList<>(Arrays.asList(someList));
        this.index = 0;
    }

    public boolean move(){
        if(this.hasNext()) {
            index++;
            return true;
        }
        else return false;
    }

    public boolean hasNext(){
        return this.theList.size() > this.index + 1;
    }

    public void print(){
        try{
            System.out.println(this.theList.get(index));
        } catch (Exception e) {
            System.out.println("Invalid Operation!");
        }
    }

    public void printAll(){
        if(this.theList.size() < 1){
            System.out.println("Invalid Operation!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        this.forEach(e -> sb.append(e + " "));
        System.out.println(sb.toString().trim());
    }
    @Override
    public Iterator<S> iterator() {
        return new ListIteratorInner();
    }

    private final class ListIteratorInner implements Iterator<S>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < theList.size();
        }

        @Override
        public S next() {
           if(!this.hasNext()){
               throw new NoSuchElementException();
           }
           return theList.get(current++);
        }
    }

   /* @Override
    public void forEach(Consumer<? super S> action) {
        while(this.theList.iterator().hasNext()){
            action.accept(theList.next());
        }
        for (int i = 0; i < theList.size(); i++) {
            action.accept(theList.get(i));
        }
    }*/

    @Override
    public Spliterator<S> spliterator() {
        return new Spliterator<S>() {

            private int ind = 0;

            @Override
            public boolean tryAdvance(Consumer<? super S> action) {
                if(ind < theList.size()){
                    action.accept(theList.get(ind++));
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<S> trySplit() {
                return null;
            }

            @Override
            public long estimateSize() {
                return Long.MAX_VALUE;
            }

            @Override
            public int characteristics() {
                return 0;
            }
        };
    }
}

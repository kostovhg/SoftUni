package i_LinkedListTraversal;

import java.util.Iterator;

public class LinkedListImpl<T> implements Iterable<T> {

    // fields/properties of current class
    private class Node<E> {

        private E value;
        private Node<E> next, previous;

        Node(E value) {
            this.setValue(value);
        }

        E getValue() {
            return this.value;
        }

        Node<E> getNext() {
            return this.next;
        }

        Node<E> getPrevious() {
            return this.previous;
        }

        void setValue(E value) {
            this.value = value;
        }

        void setNext(Node<E> next) {
            this.next = next;
        }

        void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        @Override
        public String toString(){
            return this.value.toString();
        }
    }

    private int count;
    private Node<T> first, last;
    // Constructor for this implementation

    public LinkedListImpl() {
        this.first = null;
        this.last = null;
        count = 0;
    }
    // return size of current collection

    public int getSize() {
        return this.count;
    }

    public boolean addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (this.count == 0) {
            this.first = newNode;
            this.last = newNode;
        } else {
            newNode.setNext(this.first);
            this.first.setPrevious(newNode);
            this.first = newNode;
        }
        count++;
        return true;
    }

    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);
        if (count == 0) {
            this.first = newNode;
            this.last = newNode;
        } else {
            newNode.setPrevious(this.last);
            this.last.setNext(newNode);
            this.last = newNode;
        }
        count++;
        return true;
    }

    public boolean remove(T element) {
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(element)) {
                Node<T> previous = current.getPrevious();
                Node<T> next = current.getNext();
                if(previous != null){
                    previous.setNext(next);
                } else {
                    this.first = next;
                }
                if(next != null){
                    next.setPrevious(previous);
                } else {
                    this.last = previous;
                }
                current = null;
                count--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private final class LinkedListIterator implements Iterator<T> {

        Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T val = current.getValue();
            current = current.getNext();
            return val;
        }

    }

}

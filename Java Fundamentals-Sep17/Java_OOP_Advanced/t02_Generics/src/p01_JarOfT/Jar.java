package p01_JarOfT;

import java.util.ArrayDeque;
import java.util.Deque;

public class Jar<T> {

    Deque<T> content = new ArrayDeque<>();

    public void add(T element){
        this.content.push(element);

    }

    public T remove(){
        return this.content.pop();
    }
}

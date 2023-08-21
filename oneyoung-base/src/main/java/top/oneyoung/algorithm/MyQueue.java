package top.oneyoung.algorithm;

import java.util.LinkedList;

/**
 * MyQuen
 *
 * @author oneyoung
 * @since 2023-08-20 13:59
 */

public class MyQueue<T>  {


    private LinkedList<T> data = new LinkedList<>();


    public T add(T t){
        data.addFirst(t);
        return t;
    }

    public T pop(){
        return data.pollLast();
    }


    public static void main(String[] args) {
        MyQueue<Integer> objectMyQueue = new MyQueue<>();
        objectMyQueue.add(1);
        objectMyQueue.add(2);
        System.out.println(objectMyQueue.pop());
    }
}

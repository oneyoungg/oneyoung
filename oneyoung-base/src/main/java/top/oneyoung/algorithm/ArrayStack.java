package top.oneyoung.algorithm;

import java.util.ArrayList;

/**
 * ArrayStack
 *
 * @author oneyoung
 * @since 2023-08-20 13:32
 */

public class ArrayStack<T> {
    private final ArrayList<T> data;

    public ArrayStack() {
        data = new ArrayList<>();
    }

    private T push(T t) {
        data.add(t);
        return t;
    }

    private T pop() {
        if (data.isEmpty()) {
            return null;
        }
        return data.remove(data.size() - 1);
    }

    private T peek() {
        return data.get(data.size() - 1);
    }

    public static void main(String[] args) {
        ArrayStack<Integer>
            integerArrayStack = new ArrayStack<>();
        integerArrayStack.push(1);
        integerArrayStack.push(2);
        integerArrayStack.push(3);
        System.out.println(integerArrayStack.peek());
        System.out.println(integerArrayStack.pop());
    }


}

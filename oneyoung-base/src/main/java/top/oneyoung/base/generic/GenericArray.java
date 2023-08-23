package top.oneyoung.base.generic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * GenericArray
 *
 * @author oneyoung
 * @since 2023/8/23 18:18
 */
public class GenericArray<T> {

    private T[] array;

    public GenericArray(int size, Class<T> type) {
        array = (T[]) Array.newInstance(type, size);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArray<Integer> genericArray = new GenericArray<>(10, Integer.class);
        genericArray.put(0, 1);
        Integer integer = genericArray.get(0);
        System.out.println(integer);
        Integer[] rep = genericArray.rep();
        System.out.println(rep.getClass());
        System.out.println(Arrays.toString(rep));
    }
}

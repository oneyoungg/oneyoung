package top.oneyoung.base.generic;

import java.lang.reflect.Field;

/**
 * 类型擦除
 *
 * @author oneyoung
 * @since 2023/8/23 18:05
 */
public class Remove<E extends Number, F> {
    private E e;

    private F f;

    public static void main(String[] args) {
        Remove<Integer, String> remove = new Remove<>();
        Class<? extends Remove> aClass = remove.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            String simpleName = declaredField.getType().getSimpleName();
            System.out.println(name + " " + simpleName);
        }
    }
}

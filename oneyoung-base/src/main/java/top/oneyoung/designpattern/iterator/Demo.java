package top.oneyoung.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Demo
 *
 * @author oneyoung
 * @since 2023/7/12 16:03
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        MyList strings = new MyList();
        for (String string : strings) {
            System.out.println(string);
        }
    }
}

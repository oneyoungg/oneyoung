package top.oneyoung.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * StringDemo
 *
 * @author oneyoung
 * @since 2023/7/21 021 16:54
 */

public class StringDemo {
    public static void main(String[] args) {

        String string = new String("oenyounga");

        final String s1 = "xdclass";
        String s2 = s1 + ".net";
        String s3 = "xdclass" + ".net";
        System.out.println(s2 == "xdclass.net");
        System.out.println(s3 == "xdclass.net");
        Collections.synchronizedList(new ArrayList<>());
        new CopyOnWriteArrayList<>();
    }


}

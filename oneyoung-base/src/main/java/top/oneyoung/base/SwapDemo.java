package top.oneyoung.base;

/**
 * SwapDemo
 *
 * @author oneyoung
 * @since 2023/7/21 021 16:08
 */

public class SwapDemo {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        swap2(a,b);
        System.out.println(a + " " + b);
    }

    public static void swap1(int a,int b){
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a + " " + b );
    }

    public static void swap2(int a, int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " " + b);
    }

}

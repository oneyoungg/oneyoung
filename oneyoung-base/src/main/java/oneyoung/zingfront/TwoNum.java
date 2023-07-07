package oneyoung.zingfront;

/**
 * 两个数的十位积与各位积相等
 * 使用双重循环遍历
 *
 * @author oneyoung
 * @date 2020/10/13 013 22:00
 */

public class TwoNum {
    public static void main(String[] args) {

        int AShi;
        int AGe;
        int Bshi;
        int Bge;

        for (int num = 10; num < 100; num++) {
            for (int n = num + 1; n < 100; n++) {
                AShi = num / 10;
                AGe = num % 10;
                Bshi = n / 10;
                Bge = n % 10;

                if (AShi * Bshi == AGe * Bge) {
                    System.out.println("两个数为  " + AShi + "" + AGe + "， " + Bshi + "" + Bge);
                }
            }
        }
    }
}

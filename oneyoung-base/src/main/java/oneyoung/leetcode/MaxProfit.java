package oneyoung.leetcode;

/**
 * MaxProfit
 *
 * @author oneyoung
 * @since 2022/2/22 10:55 AM
 */
public class MaxProfit {

    public static void main(String[] args) {
        int i = maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {
        int head = 0;
        int count = 0;
        int before = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < before) {
                System.out.println("i = " + i);
                System.out.println("head = " + head);
                count += prices[i - 1] - prices[head];
                head = i;
            }
        }
        return count;
    }
}

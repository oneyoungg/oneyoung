package top.oneyoung.zingfront;

/**
 * coin
 *
 *
 * @author oneyoung
 * @date 2020/10/13 013 00:17
 */

public class coin {

    public static void main(String[] args) {
        coin(5,10);
    }

    /**
     *
     * @param n
     * @param m
     */
    private static void coin(int n, int m) {
        // 10分
        for (int i = 0; i < m / n + 1; i++) {
            // 5分
            for (int j = 0; j < (m - 10 * i + 1); j++) {
                // 2分
                for (int k = 0; k < m - 10 * i - 5 * j + 1; k++) {
                    // 1分
                    int l = m - 10 * i - 5 * j - 2 * k;
                    // 如果所有游戏币相加==n  且 1分硬币不为负
                    if (i + j + k + l == n && l >= 0) {
                        System.out.println("10分有" + i + "个  " + "5分有" + j + "个  " + "2分有" + k + "个  " + "1分有" + l + "个");
                    }
                }
            }
        }
    }
}

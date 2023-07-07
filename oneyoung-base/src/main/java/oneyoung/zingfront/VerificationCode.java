package oneyoung.zingfront;

import java.util.Scanner;

/**
 * 首先逆序字符串，然后遍历字符串，按照要求进行运算
 * 关键在于获取字符对应的数字
 *
 * @author oneyoung
 * @date 2020/10/12 012 23:32
 */

public class VerificationCode {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        System.out.print(verify(str) ? "ok" : "error");
    }

    private static boolean verify(String str) throws Exception {
        StringBuilder sb = new StringBuilder();
        // 逆序字符串
        for (int i = str.length() - 1; i >= 0; i--) {
            char get = str.charAt(i);
            sb.append(get);
        }
        String s = sb.toString();
        int jsum = 0, osum = 0;
        for (int i = 0; i < s.length(); i++) {
            // 获取字符对应的数字
            int get = getMapData(s.charAt(i));
            if ((i + 1) % 2 == 0) {
                get = 2 * get;
                if (get >= 10) {
                    get = get - 9;
                }
                osum += get;
            } else {
                jsum += get;
            }
        }
        return (osum + jsum) % 10 == 0;
    }

    /**
     * 获取映射的值
     *
     * @param c 传入的字符
     * @return c的实际值
     */
    public static int getMapData(char c) throws Exception {
        int result;
        // 如果是小写字母，则按规则映射成数字
        if (c >= 97 && c <= 122) {
            result = (c - 96) % 9;
        } else if (c >= 48 && c <= 57) {
            result = c - 48;
        } else {
            // 非数字和小写字母则抛出异常
            throw new Exception("数据错误");
        }
        return result;
    }

}

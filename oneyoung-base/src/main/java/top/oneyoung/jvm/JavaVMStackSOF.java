package top.oneyoung.jvm;

/**
 * JavaVMStackSOF
 *
 * @author oneyoung
 * @since 2023/7/14 13:39
 */

/**
 * VM Args:-Xss128k * @author zzm
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}

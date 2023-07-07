package oneyoung.inerclass;

/**
 * TestIner
 * <p>
 * 2020/8/9 009 23:22
 *
 * @author oneyoung
 */

public class testInner {
    private boolean flag = true;

    public class innerClazz{
        public void innerTest(){
            System.out.println(flag);
        }
    }

    public void test(){
        new innerClazz().innerTest();
        System.out.println(flag);
    }

    public static void main(String[] args) {
        new testInner().test();
        testInner testInner = new testInner();
        testInner.setFlag(false);
        testInner.test();
        System.out.println();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

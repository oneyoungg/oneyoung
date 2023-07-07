package top.oneyoung.designpattern.singleton;

/**
 * SingletonObject4
 *
 * @author oneyoung
 * @date 2020/8/7 007 17:49
 */


public class SingletonObject4 {

    private SingletonObject4() {
    }

    private static class InstanceHolder{
        private static final SingletonObject4 INSTANCE = new SingletonObject4();
    }

    public static SingletonObject4 getInstance(){
        return InstanceHolder.INSTANCE;
    }

}

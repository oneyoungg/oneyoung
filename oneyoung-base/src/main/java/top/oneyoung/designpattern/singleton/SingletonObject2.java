package top.oneyoung.designpattern.singleton;

/**
 * SingletonObject2
 *
 * @author oneyoung
 * @date 2020/8/7 007 17:49
 */


public class SingletonObject2 {
    // 定义静态变量时，未初始化实例
    private static SingletonObject2 instance;

    // 私有化构造函数
    private SingletonObject2(){

    }

    public static SingletonObject2 getInstance(){
        // 使用时，先判断实例是否为空，如果实例为空，则实例化对象
        if (instance == null) {
            instance = new SingletonObject2();
        }
        return instance;
    }
}

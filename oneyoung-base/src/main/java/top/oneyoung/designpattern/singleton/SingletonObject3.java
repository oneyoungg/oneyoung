package top.oneyoung.designpattern.singleton;

/**
 * SingletonObject2
 *
 * @author oneyoung
 * @date 2020/8/7 007 17:49
 */


public class SingletonObject3 {
    /**
     * 定义静态变量时，未初始化实例
     * 要解决双重检查锁模式带来空指针异常的问题，只需要使用volatile关键字，volatile关键字严格遵循happens-before原则，即在读操作前，写操作必须全部完成。
     */
    private volatile static SingletonObject3 instance;
    private static final Object OBJECT = new Object();

    /**
     * 私有化构造函数
     */
    private SingletonObject3(){

    }

    public static SingletonObject3 getInstance(){

        // 使用时，先判断实例是否为空，如果实例为空，则实例化对象
        if (instance == null) {
            // 使用互斥锁 只允许单一线程初始化实例
            synchronized (OBJECT){
                if (instance == null) {
                    instance = new SingletonObject3();
                }
            }
        }
        return instance;
    }
}

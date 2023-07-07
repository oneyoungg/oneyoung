package oneyoung.designpattern.singleton;

/**
 * SingletonObject1
 *
 * @author oneyoung
 * @date 2020/8/7 007 17:48
 */

//在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快
public class SingletonObject1 {
    // 利用静态变量来存储唯一实例
    private static final SingletonObject1 instance = new SingletonObject1();

    // 私有化构造函数
    private SingletonObject1(){
        // 里面可能有很多操作
    }

    // 提供公开获取实例接口
    public static SingletonObject1 getInstance(){
        return instance;
    }
}

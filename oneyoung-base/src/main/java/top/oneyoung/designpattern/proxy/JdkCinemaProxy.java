package top.oneyoung.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JdkCinemaProxy
 *
 * @author oneyoung
 * @since 2021/12/31 2:47 PM
 */
public class JdkCinemaProxy implements InvocationHandler {

    private final Object object;

    public JdkCinemaProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = null;
        if (method.getName().equals("play")) {
            CinemaProxyTask.playStartAd();
            invoke = method.invoke(object, args);
            CinemaProxyTask.playEndAd();
        }
        return invoke;
    }
}

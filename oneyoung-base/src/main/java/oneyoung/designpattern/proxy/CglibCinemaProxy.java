package oneyoung.designpattern.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibCinemaProxy
 *
 * @author oneyoung
 * @since 2021/12/31 3:50 PM
 */
public class CglibCinemaProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println("o = " + o);
        System.out.println("method = " + method);
        CinemaProxyTask.playStartAd();
        Object invoke = methodProxy.invokeSuper(o, objects);
        CinemaProxyTask.playEndAd();
        return invoke;
    }
}

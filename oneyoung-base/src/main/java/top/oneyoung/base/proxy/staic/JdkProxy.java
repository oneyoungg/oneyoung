package top.oneyoung.base.proxy.staic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JdkProxy
 *
 * @author oneyoung
 * @since 2023-08-23 23:33
 */

public class JdkProxy implements InvocationHandler {

    private Object targetObject;

    public Object getProxyInstance(Object targetobject) {
        this.targetObject = targetobject;

        return Proxy.newProxyInstance(targetobject.getClass().getClassLoader(),
                targetobject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            openTransaction();
            Object invoke = method.invoke(targetObject, args);
            commitTransaction();
            return invoke;
    }

    public static void openTransaction() {
        System.out.println("open transaction");
    }

    public static void commitTransaction() {
        System.out.println("commit transaction");
    }
}

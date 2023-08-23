package top.oneyoung.base.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Methedtest
 *
 * @author oneyoung
 * @since 2023/8/23 19:35
 */
public class MethedTest {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        Class<? extends Job> aClass = job.getClass();
        System.out.println(aClass.getName());
        System.out.println(aClass.getSuperclass().getName());
        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }

//        Method[] methods = aClass.getMethods();
        Method[] declaredMethods = aClass.getMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName() + " " + declaredMethod.getReturnType().getName() + " " + declaredMethod);
        }

        Field jobName = aClass.getDeclaredField("jobName");
        jobName.setAccessible(true);
        Job obj = new Job();
        Object o = jobName.get(obj);
        System.out.println(o);
        jobName.set(obj, "1");
//        System.out.println(jobName.get(obj));

        Method toString1 = aClass.getDeclaredMethod("toString");
        toString1.setAccessible(true);
        Object toString = toString1.invoke(obj);
        System.out.println(toString);
    }
}

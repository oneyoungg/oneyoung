package top.oneyoung.base.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

/**
 * ReflexTest
 *
 * @author oneyoung
 * @since 2023/8/23 18:53
 */
public class ReflexTest {
    public static void main(String[] args) throws Exception {
        Par par = new Par();
        Class<? extends Par> aClass = par.getClass();
        Class<Par> parClass = Par.class;
        Class<?> aClass1 = Class.forName("top.oneyoung.base.reflex.Par");
        Class aClass2 = ClassLoader.getSystemClassLoader().loadClass("top.oneyoung.base.reflex.Par");
        System.out.println(aClass == parClass);
        System.out.println(aClass == aClass1);
        System.out.println(aClass == aClass2);

        Constructor<Par> declaredConstructor = parClass.getDeclaredConstructor(String.class);
        Constructor<?>[] declaredConstructors = parClass.getConstructors();
        declaredConstructor.setAccessible(true);
        for (Constructor<?> constructor : declaredConstructors) {
            String constructorName = constructor.getName();
            System.out.print(constructorName + " ");
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                System.out.print(parameter.getName() + " ");
            }
            System.out.println();
        }

//        Par par1 = declaredConstructor.newInstance("1");
//        System.out.println(par1);
    }
}

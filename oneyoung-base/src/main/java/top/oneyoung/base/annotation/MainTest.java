package top.oneyoung.base.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * MainTest
 *
 * @author oneyoung
 * @since 2023-08-23 23:10
 */

public class MainTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<AnnTest> annTestClass = AnnTest.class;
        Method[] declaredMethods = annTestClass.getDeclaredMethods();
        List<AnnTest.Task> list = new ArrayList<>();
        for (Method declaredMethod : declaredMethods) {
            Test test = declaredMethod.getDeclaredAnnotation(Test.class);
            int priority = 0;
            boolean disabled = false;
            if (test != null) {
                priority = test.priority();
                disabled = test.disabled();
            }
            list.add(new AnnTest.Task(declaredMethod, priority, disabled));
        }
        list.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
        for (AnnTest.Task task : list) {
            if (!task.disabled && !task.getMethod().getName().equals("main")) {
                task.getMethod().invoke(new AnnTest());
            }
        }
    }
}

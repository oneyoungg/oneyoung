package top.oneyoung.base.annotation;

import lombok.Getter;

import java.lang.reflect.Method;

/**
 * AnnTest
 *
 * @author oneyoung
 * @since 2023-08-23 22:34
 */

public class AnnTest {


    @Getter
    static class Task implements Comparable<Task> {
        public Method method;

        public int priority;

        public boolean disabled;

        public Task(Method method, int priority, boolean disabled) {
            this.method = method;
            this.priority = priority;
            this.disabled = disabled;
        }

        @Override
        public int compareTo(Task o) {
            return this.priority - o.priority;
        }
    }

    @Test(priority = 10)
    public void test1() {
        System.out.println("test1");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("test2");
    }

    @Test(priority = 3, disabled = true)
    public void test3() {
        System.out.println("test3");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("test4");
    }
}

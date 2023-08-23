package top.oneyoung.base.reflex;

import java.lang.reflect.Method;

/**
 * MethodTest1
 *
 * @author oneyoung
 * @since 2023/8/23 19:55
 */
public class MethodTest1 {
    public static void main(String[] args) throws Exception {
        User user = new User();
        Class<? extends User> userClass = user.getClass();
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName() + " " + declaredMethod.getReturnType().getName() + " " + declaredMethod);
        }
        Method getStatic = userClass.getDeclaredMethod("getStatic");
        getStatic.setAccessible(true);
        System.out.println(getStatic.invoke(null));
    }


    static class User {
        private String name;
        private Integer age;

        public User() {
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        private String getName() {
            return name;
        }

        public String getAge() {
            return age.toString();
        }

        private static String getStatic() {
            return "static";
        }
    }
}

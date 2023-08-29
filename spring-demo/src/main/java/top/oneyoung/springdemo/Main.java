package top.oneyoung.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.oneyoung.springdemo.color.Color;
import top.oneyoung.springdemo.factorybean.Order;

import java.util.Map;

/**
 * Main
 *
 * @author oneyoung
 * @since 2023/8/28 10:26
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        System.out.println("============================");
        Order bean = applicationContext.getBean(Order.class);
        System.out.println(bean);
        applicationContext.close();
    }

    public static void annotationConfigApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("top.oneyoung");
        System.out.println("============================");
        Map<String, Color> beansOfType = context.getBeansOfType(Color.class);
        beansOfType.forEach((k, v) -> System.out.println(k + " : " + v.getColor()));
    }

    public static void xml(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        System.out.println("============================");
        Map<String, Color> beansOfType = applicationContext.getBeansOfType(Color.class);
        beansOfType.forEach((k, v) -> System.out.println(k + " : " + v.getColor()));
        Object product = applicationContext.getBean("product");
        Object product1 = applicationContext.getBean("product");
        System.out.println(product);
        System.out.println(product1);
        System.out.println(product1 == product);
    }
}

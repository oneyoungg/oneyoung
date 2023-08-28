package top.oneyoung.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.oneyoung.springdemo.color.Color;

import java.util.Map;

/**
 * Main
 *
 * @author oneyoung
 * @since 2023/8/28 10:26
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("top.oneyoung");
        System.out.println("============================");
        Map<String, Color> beansOfType = context.getBeansOfType(Color.class);
        beansOfType.forEach((k, v) -> System.out.println(k + " : " + v.getColor()));
    }
}

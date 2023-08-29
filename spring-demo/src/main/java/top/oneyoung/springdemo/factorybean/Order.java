package top.oneyoung.springdemo.factorybean;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Order
 *
 * @author oneyoung
 * @since 2023/8/29 11:20
 */
@Getter
public class Order implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;

    private String color;

    private int price;

    public Order() {
        System.out.println("order constructor");
    }

    public void init() {
        System.out.println("order init");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("order setBeanFactory " + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("order setBeanName " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("order setApplicationContext " + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("order afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("order destroy");
    }

    public void setName(String name) {
        System.out.println("order setName " + name);
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
            "name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", price=" + price +
            '}';
    }
}

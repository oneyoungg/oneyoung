package top.oneyoung.springdemo.factorybean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * CustomBeanPostProcessor
 *
 * @author oneyoung
 * @since 2023/8/29 11:15
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

    public CustomBeanPostProcessor() {
        System.out.println("CustomBeanPostProcessor constructor");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization: " + beanName + " : " + bean.getClass());
        if (bean instanceof Order) {
            Order product = (Order) bean;
            product.setColor("before");
            return product;
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization: " + beanName + " : " + bean.getClass());
        if (bean instanceof Order) {
            Order product = (Order) bean;
            product.setPrice(1);
            return product;
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

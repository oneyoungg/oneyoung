package top.oneyoung.springdemo.factorybean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * CustomBeanFactoryPostProcessor
 *
 * @author oneyoung
 * @since 2023/8/29 11:01
 */
@Slf4j
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public CustomBeanFactoryPostProcessor() {
        System.out.println("CustomBeanFactoryPostProcessor constructor");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanFactoryPostProcessor postProcessBeanFactory");
    }
}


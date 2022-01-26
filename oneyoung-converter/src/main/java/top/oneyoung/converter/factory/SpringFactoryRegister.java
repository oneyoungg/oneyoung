package top.oneyoung.converter.factory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import top.oneyoung.converter.Converter;
import top.oneyoung.converter.Filler;
import top.oneyoung.converter.Merger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 集成到Spring里面
 *
 * @author oneyoung
 * @see org.springframework.core.convert.converter.ConverterFactory spring的实现是用明确的输入类找输出工厂类，需要知道输入类，这里的实现通过输入类的类型获取。
 * @since 18/10/23
 */
public class SpringFactoryRegister implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringFactoryRegister.class);
    private final AtomicBoolean hasProcessed = new AtomicBoolean();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // do nothing
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Converter) {
            ConverterFactory.register((Converter<?, ?>) bean);
        } else if (bean instanceof Merger) {
            MergerFactory.register((Merger<?, ?>) bean);
        } else if (bean instanceof Filler) {
            FillerFactory.register((Filler<?, ?>) bean);
        }
        return bean;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasProcessed.compareAndSet(false, true)) {
            System.out.println("Ignore Spring & SpringMVC ContextRefreshedEvent Second Times.");
            return;
        }
        for (String s : ConverterFactory.convertListString()) {

        }
    }

    @Configuration
    public static class FactoryAutoConfiguration {

        @Bean
        public SpringFactoryRegister converterFactorySpring() {
            return new SpringFactoryRegister();
        }
    }
}

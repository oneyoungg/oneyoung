package top.ooneyoung.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * CustomBeanPostProcessor
 *
 * @author oneyoung
 * @since 2023/8/29 11:15
 */
@Slf4j
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    public CustomBeanPostProcessor() {
        log.info("CustomBeanPostProcessor constructor");
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessAfterInitialization: " + beanName + " : " + bean.getClass());
        if (bean instanceof RabbitTemplate) {
            RabbitTemplate rabbitTemplate = (RabbitTemplate) bean;
            log.info("config rabbit");
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//                log.info("===============client -> exchange start ====================");
//                log.info("correlationData: {}", correlationData);
//                log.info("ack: {}", ack);
//                log.info("cause: {}", cause);
//                log.info("===============client -> exchange end ====================");
            });

            rabbitTemplate.setReturnsCallback(returned -> {
                log.info("===============exchange -> queue start ====================");
                log.info("returned: {}", returned);
                log.info("===============exchange -> queuen end ====================");
            });
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

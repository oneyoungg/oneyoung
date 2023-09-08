package top.ooneyoung.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

/**
 * RabbitTemConfig
 *
 * @author oneyoung
 * @since 2023/9/8 17:26
 */
@Slf4j
//@Configuration
public class RabbitTemConfig {

    @Bean
//    @ConditionalOnBean(RabbitTemplate.class)
    public String rabbit(RabbitTemplate rabbitTemplate) {
        log.info("config rabbit");
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("correlationData: {}", correlationData);
            log.info("ack: {}", ack);
            log.info("cause: {}", cause);
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("returned: {}", returned);
        });
        return "hello";
    }
}

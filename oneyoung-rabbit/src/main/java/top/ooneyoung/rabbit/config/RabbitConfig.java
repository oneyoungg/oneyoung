package top.ooneyoung.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitConfig
 *
 * @author oneyoung
 * @since 2023/9/8 13:53
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Exchange exchange() {
        // durable: 是否持久化
        // autoDelete: 是否自动删除
        return new TopicExchange("exchange", true, false);
    }

    @Bean
    public Queue queue(){
        return new Queue("queue1", true, false, false);
    }

    @Bean
    public Queue queue1(){
        return new Queue("queue2", true, false, false);
    }


    @Bean
    public Binding binding(){
        return new Binding("queue1", Binding.DestinationType.QUEUE,"exchange", "routekey.*",null);
    }

    @Bean
    public Binding binding1(){
        return new Binding("queue2", Binding.DestinationType.QUEUE,"exchange", "routekey.*",null);
    }
}

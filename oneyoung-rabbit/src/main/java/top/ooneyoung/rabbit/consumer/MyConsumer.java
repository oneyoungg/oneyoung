package top.ooneyoung.rabbit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * MyConsumer
 *
 * @author oneyoung
 * @since 2023/9/8 14:06
 */
@Slf4j
@Component
public class MyConsumer {

    @RabbitListener(queues = "queue1")
    public void receive(String message, Message messageInfo) {
        System.out.println("queue1 receive message: " + message);
        log.info("queue1 receive message: {} {}", message, messageInfo);
    }

    @RabbitListener(queues = "queue2")
    public void receive2(String message, Message messageInfo) {
        System.out.println("queue2 receive message: " + message);
        log.info("queue2 receive message: {} {}", message, messageInfo);
    }

}

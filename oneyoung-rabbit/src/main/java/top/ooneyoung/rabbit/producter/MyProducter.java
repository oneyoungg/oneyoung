package top.ooneyoung.rabbit.producter;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MyProducter
 *
 * @author oneyoung
 * @since 2023/9/8 14:05
 */
@Component
public class MyProducter {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(String message, String routeKey) {
        rabbitTemplate.convertAndSend("exchange", routeKey, message, message1 -> {
            message1.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message1;
        });
    }
}

package top.ooneyoung.rabbit.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    public void receive(String message, Message messageInfo, Channel channel) throws IOException {
        System.out.println("queue1 receive message: " + message);
        log.info("queue1 receive message: {} {}", message, messageInfo);
        long deliveryTag = messageInfo.getMessageProperties().getDeliveryTag();
        // 手动ack
//        channel.basicAck(deliveryTag, false);
        // nack
        channel.basicNack(deliveryTag, false, false);
//        channel.basicReject(deliveryTag, true);
    }

    @RabbitListener(queues = "queue2")
    public void receive2(String message, Message messageInfo) {
        System.out.println("queue2 receive message: " + message);
        log.info("queue2 receive message: {} {}", message, messageInfo);
    }

    @RabbitListener(queues = "bitch.queue")
    public void recevue3(String message, Message messageInfo, Channel channel) throws Exception {
        System.out.println("queue2 receive message: " + message);
        log.info("bitch.queue receive message: {} {}", message, messageInfo);
        TimeUnit.MILLISECONDS.sleep(10);
        long deliveryTag = messageInfo.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }


}

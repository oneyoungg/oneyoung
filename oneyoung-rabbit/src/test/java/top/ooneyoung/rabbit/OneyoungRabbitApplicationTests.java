package top.ooneyoung.rabbit;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OneyoungRabbitApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        for (int i = 1; i <=10000; i++) {
            rabbitTemplate.convertAndSend("exchange", "bitch", "msg" + i);
        }

    }

}

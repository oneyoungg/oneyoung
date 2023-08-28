package top.oneyoung.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.oneyoung.sms.starter.SmsService;

/**
 * SmsConfig
 *
 * @author oneyoung
 * @since 2023/8/28 16:19
 */
@Configuration
public class SmsConfig {

    @Bean
    public SmsService smsService() {
        return new SmsService(){
            @Override
            public void send(String mobile, String code) {
                System.out.println("自定义短信发送逻辑");
            }
        };
    }
}

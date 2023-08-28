package top.oneyoung.sms.starter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.oneyoung.sms.starter.SmsService;

/**
 * SmsAutoConfiguration
 *
 * @author oneyoung
 * @since 2023/8/28 14:50
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass(SmsService.class)
@EnableConfigurationProperties(SmsProperties.class)
public class SmsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SmsService.class)
    public SmsService smsService(SmsProperties smsProperties) {
        log.info("smsProperties:{}", smsProperties);
        return new SmsService() {
            @Override
            public void send(String mobile, String code) {
                log.info("发送短信验证码：{}到手机：{}", code, mobile);
                System.out.println("发送短信验证码：" + code + "到手机：" + mobile);
            }
        };
    }

}

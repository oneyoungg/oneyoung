package top.oneyoung.sms.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SmsProperties
 *
 * @author oneyoung
 * @since 2023/8/28 14:48
 */
@Data
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {


    private String accessKeyId;

    private String accessKeySecret;

    private String signName;

    private String templateCode;



}

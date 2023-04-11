package top.oneyoung.converter.starter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ConverterConfigProperty 配置类
 *
 * @author oneyoung
 * @since 2022/4/15 21:44
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "oneyoung.converter")
public class ConverterConfigProperty {
    private boolean autoConvert = false;
}
